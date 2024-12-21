package com.songshilong.framework.starter.idempotent.core.spel;

import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.idempotent.annotations.Idempotent;
import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentAspect;
import com.songshilong.framework.starter.idempotent.core.IdempotentContext;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import com.songshilong.framework.starter.idempotent.toolkit.SpELUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.spel
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:49
 * @Description: IdempotentSpELByRestAPIExecuteHandler
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class IdempotentSpELByRestAPIExecuteHandler extends AbstractIdempotentExecuteHandler implements  IdempotentSpELService{


    private final RedissonClient redissonClient;
    private final static String LOCK = "lock:spEL:restAPI";


    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        Idempotent idempotent = null;
        try {
            idempotent = IdempotentAspect.getIdempotent(joinPoint);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParamWrapper.builder().lockKey(key).joinPoint(joinPoint).build();
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        String uniqueKey = wrapper.getIdempotent().uniqueKeyPrefix() + wrapper.getLockKey();
        RLock lock = redissonClient.getLock(uniqueKey);
        if (!lock.tryLock()) {
            throw new ClientException(wrapper.getIdempotent().message());
        }
        IdempotentContext.put(LOCK, lock);
    }

    @Override
    public void postProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    @Override
    public void exceptionProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
