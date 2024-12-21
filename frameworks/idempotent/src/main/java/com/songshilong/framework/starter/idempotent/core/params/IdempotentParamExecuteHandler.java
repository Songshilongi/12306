package com.songshilong.framework.starter.idempotent.core.params;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentContext;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import com.songshilong.framework.starter.user.core.UserContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.Watchable;
import java.util.Objects;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.params
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:40
 * @Description: IdempotentParamExecuteHandler
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class IdempotentParamExecuteHandler extends AbstractIdempotentExecuteHandler implements IdempotentParamService {

    private final RedissonClient redissonClient;
    private final static String LOCK_KEY = "lock:param:restAPI";


    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        String lockKey = String.format("idempotent:path:%s:currentUserId:%s:md5:%s", this.getServletPath(), this.getCurrentUserId(), this.calcArgsMd5(joinPoint));
        return IdempotentParamWrapper.builder().lockKey(lockKey).joinPoint(joinPoint).build();
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        String lockKey = wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            throw new ClientException(wrapper.getIdempotent().message());
        }
        IdempotentContext.put(LOCK_KEY, lock);
    }

    @Override
    public void postProcessing() {
        RLock rLock = null;
        try {
            rLock = (RLock) IdempotentContext.getKey(LOCK_KEY);
        } finally {
            if (null != rLock) {
                rLock.unlock();
            }
        }
    }

    @Override
    public void exceptionProcessing() {
        this.postProcessing();
    }

    private String getServletPath() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(servletRequestAttributes)) {
            return StrUtil.EMPTY;
        }
        return servletRequestAttributes.getRequest().getServletPath();
    }

    private String getCurrentUserId() {
        String userId = UserContext.getUserId();
        if (StrUtil.isEmpty(userId)) {
            throw new ClientException("请登录!");
        }
        return userId;
    }

    private String calcArgsMd5(ProceedingJoinPoint joinPoint) {
        return DigestUtil.md5Hex(JSON.toJSONBytes(joinPoint.getArgs()));
    }

}
