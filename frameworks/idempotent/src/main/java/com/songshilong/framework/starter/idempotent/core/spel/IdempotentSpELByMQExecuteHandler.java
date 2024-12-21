package com.songshilong.framework.starter.idempotent.core.spel;

import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.spel
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:40
 * @Description: IdempotentSpELByMQExecuteHandler
 * @Version: 1.0
 */
public class IdempotentSpELByMQExecuteHandler extends AbstractIdempotentExecuteHandler implements IdempotentSpELService{
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        return null;
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {

    }
}
