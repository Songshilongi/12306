package com.songshilong.service.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.framework.starter.cache.DistributedCache;
import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.convention.exception.ServiceException;
import com.songshilong.framework.starter.user.core.UserInfoDTO;
import com.songshilong.service.user.dao.entity.UserDO;
import com.songshilong.service.user.dao.entity.UserMailDO;
import com.songshilong.service.user.dao.entity.UserPhoneDO;
import com.songshilong.service.user.dao.mapper.UserMailMapper;
import com.songshilong.service.user.dao.mapper.UserMapper;
import com.songshilong.service.user.dao.mapper.UserPhoneMapper;
import com.songshilong.service.user.dto.request.UserLoginReqDTO;
import com.songshilong.service.user.dto.response.UserLoginRespDTO;
import com.songshilong.service.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.songshilong.framework.starter.user.toolkit.JwtUtil;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service.impl
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:52
 * @Description: UserLoginServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private final UserMailMapper userMailMapper;
    private final UserPhoneMapper userPhoneMapper;
    private final UserMapper userMapper;

    private final DistributedCache distributedCache;


    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        String usernameOrMailOrPhone = requestParam.getUsernameOrMailOrPhone();
        boolean mailFlag = false;
        // 时间复杂度最佳 O(1)。indexOf or contains 时间复杂度为 O(n)
        for (char c : usernameOrMailOrPhone.toCharArray()) {
            if (c == '@') {
                mailFlag = true;
                break;
            }
        }
        String username;
        if (mailFlag) {
            LambdaQueryWrapper<UserMailDO> queryWrapper = Wrappers.lambdaQuery(UserMailDO.class)
                    .eq(UserMailDO::getMail, usernameOrMailOrPhone);
            username = Optional.ofNullable(userMailMapper.selectOne(queryWrapper))
                    .map(UserMailDO::getUsername)
                    .orElseThrow(() -> new ClientException("用户名/手机号/邮箱不存在"));
        } else {
            LambdaQueryWrapper<UserPhoneDO> queryWrapper = Wrappers.lambdaQuery(UserPhoneDO.class)
                    .eq(UserPhoneDO::getPhone, usernameOrMailOrPhone);
            username = Optional.ofNullable(userPhoneMapper.selectOne(queryWrapper))
                    .map(UserPhoneDO::getUsername)
                    .orElse(null);
        }
        username = Optional.ofNullable(username).orElse(requestParam.getUsernameOrMailOrPhone());
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username)
                .eq(UserDO::getPassword, requestParam.getPassword())
                .select(UserDO::getId, UserDO::getUsername, UserDO::getRealName);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if (userDO != null) {
            UserInfoDTO userInfo = UserInfoDTO.builder()
                    .userId(String.valueOf(userDO.getId()))
                    .username(userDO.getUsername())
                    .realName(userDO.getRealName())
                    .build();
            String accessToken = JwtUtil.generateAccessToken(userInfo);
            UserLoginRespDTO actual = new UserLoginRespDTO(userInfo.getUserId(), requestParam.getUsernameOrMailOrPhone(), userDO.getRealName(), accessToken);
            distributedCache.put(accessToken, JSON.toJSONString(actual), 30, TimeUnit.MINUTES);
            return actual;
        }
        throw new ServiceException("账号不存在或密码错误");
    }
}
