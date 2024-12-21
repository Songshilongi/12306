package com.songshilong.framework.starter.idempotent.core.token;

import com.songshilong.framework.starter.idempotent.core.AbstractIdempotentExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.IdempotentParamWrapper;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.token
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:40
 * @Description: IdempotentTokenExecuteHandler
 * @Version: 1.0
 */
public class IdempotentTokenExecuteHandler extends AbstractIdempotentExecuteHandler implements IdempotentTokenService {
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        return null;
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {

    }
}
