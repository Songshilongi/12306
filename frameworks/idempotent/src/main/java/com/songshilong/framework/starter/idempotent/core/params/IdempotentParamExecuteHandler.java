package com.songshilong.framework.starter.idempotent.core.params;

import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.params
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:40
 * @Description: IdempotentParamExecuteHandler
 * @Version: 1.0
 */
public class IdempotentParamExecuteHandler extends AbstractIdempotentExecuteHandler {
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        return null;
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {

    }
}
