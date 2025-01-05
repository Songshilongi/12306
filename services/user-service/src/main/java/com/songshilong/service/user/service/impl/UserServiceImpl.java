package com.songshilong.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.framework.starter.cache.DistributedCache;
import com.songshilong.framework.starter.common.toolkit.BeanUtil;
import com.songshilong.framework.starter.convention.exception.ServiceException;
import com.songshilong.framework.starter.designpattern.chain.AbstractChainContext;
import com.songshilong.service.user.constant.RedisConstant;
import com.songshilong.service.user.dao.entity.UserDO;
import com.songshilong.service.user.dao.entity.UserDeletionDO;
import com.songshilong.service.user.dao.entity.UserMailDO;
import com.songshilong.service.user.dao.entity.UserPhoneDO;
import com.songshilong.service.user.dao.mapper.UserDeletionMapper;
import com.songshilong.service.user.dao.mapper.UserMailMapper;
import com.songshilong.service.user.dao.mapper.UserMapper;
import com.songshilong.service.user.dao.mapper.UserPhoneMapper;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.dto.response.UserRegisterRespDTO;
import com.songshilong.service.user.enums.UserChainMarkEnum;
import com.songshilong.service.user.enums.UserRegisterErrorCodeEnum;
import com.songshilong.service.user.service.UserService;
import com.songshilong.service.user.toolkit.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service.impl
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  18:22
 * @Description: UserInfoServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMailMapper userMailMapper;
    private final UserPhoneMapper userPhoneMapper;
    private final UserDeletionMapper userDeletionMapper;
    /*
    当前只有一个布隆过滤器，可以直接注入
     */
    private final RBloomFilter<String> usernameBloomFilter;
    private final DistributedCache distributedCache;
    private final AbstractChainContext<UserRegisterReqDTO> abstractChainContext;
    private final RedissonClient redissonClient;


    @Override
    public Long queryUserDeletionCount(Integer idType, String idCard) {
        Long deletionCount = null;
        String redisKey = RedisConstant.ID_TYPE_CARD_REGISTER_COUNT + idType + "_" + idCard;
        deletionCount = distributedCache.get(redisKey, Long.class);
        if (Objects.nonNull(deletionCount)) {
            return deletionCount;
        }
        LambdaQueryWrapper<UserDeletionDO> queryWrapper = Wrappers.lambdaQuery(UserDeletionDO.class)
                .eq(UserDeletionDO::getIdType, idType)
                .eq(UserDeletionDO::getIdCard, idCard);
        deletionCount = userDeletionMapper.selectCount(queryWrapper);
        if (Objects.nonNull(deletionCount)) {
            distributedCache.put(redisKey, deletionCount, 60, TimeUnit.MINUTES);
            return deletionCount;
        }
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRegisterRespDTO register(UserRegisterReqDTO requestParams) {
        abstractChainContext.handler(UserChainMarkEnum.USER_REGISTER_FILTER.name(), requestParams);
        // 用户名是唯一的，所以需要加锁，防止重复注册，同一时刻下， 只有一个线程可以操作用户名
        String username = requestParams.getUsername();
        RLock lock = redissonClient.getLock(RedisConstant.LOCK_REGISTER_USERNAME + username);
        if (!lock.tryLock()) {
            throw new ServiceException(UserRegisterErrorCodeEnum.HAS_USERNAME_NOTNULL);
        }
        try {
            try {
                int insert = this.userMapper.insert(BeanUtil.convert(requestParams, UserDO.class));
                if (insert != 1) {
                    throw new ServiceException(UserRegisterErrorCodeEnum.USER_REGISTER_FAIL);
                }
            } catch (DuplicateKeyException ex) {
                log.error("用户名重复注册 {}", username);
                throw new ServiceException(UserRegisterErrorCodeEnum.HAS_USERNAME_NOTNULL);
            }
            UserPhoneDO userPhoneDO = UserPhoneDO.builder()
                    .phone(requestParams.getPhone())
                    .username(username)
                    .build();
            try {
                int insert = userPhoneMapper.insert(userPhoneDO);
                if (insert != 1) {
                    throw new ServiceException(UserRegisterErrorCodeEnum.PHONE_REGISTERED);
                }
            } catch (DuplicateKeyException ex) {
                log.error("手机号重复注册 {}", requestParams.getPhone());
                throw new ServiceException(UserRegisterErrorCodeEnum.PHONE_REGISTERED);
            }

            if (StringUtils.isNotBlank(requestParams.getMail())) {
                UserMailDO userMailDO = UserMailDO.builder()
                        .mail(requestParams.getMail())
                        .username(username)
                        .build();
                try {
                    int insert = userMailMapper.insert(userMailDO);
                    if (insert != 1) {
                        throw new ServiceException(UserRegisterErrorCodeEnum.MAIL_REGISTERED);
                    }
                } catch (DuplicateKeyException ex) {
                    log.error("邮箱重复注册 {}", requestParams.getMail());
                    throw new ServiceException(UserRegisterErrorCodeEnum.MAIL_REGISTERED);
                }
            }
            // 保存到Redis 用户名集合里面
            StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) distributedCache.getInstance();
            stringRedisTemplate.opsForSet().add(RedisConstant.REGISTER_USERNAME + UserUtils.hashShardingIdx(username), username);


        } finally {
            lock.unlock();
        }
        return BeanUtil.convert(requestParams, UserRegisterRespDTO.class);
    }


    @Override
    public Boolean hasUsername(String username) {
        boolean contains = usernameBloomFilter.contains(username);
        if (contains) {
            // 判断是否是误判
            StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) distributedCache.getInstance();
            return stringRedisTemplate.opsForSet().isMember(RedisConstant.REGISTER_USERNAME + UserUtils.hashShardingIdx(username), username);

        }
        return Boolean.FALSE;
    }
}
