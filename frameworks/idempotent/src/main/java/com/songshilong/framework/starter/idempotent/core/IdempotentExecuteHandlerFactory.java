package com.songshilong.framework.starter.idempotent.core;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import com.songshilong.framework.starter.idempotent.core.params.IdempotentParamService;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELByMQExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELByRestAPIExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELService;
import com.songshilong.framework.starter.idempotent.core.token.IdempotentTokenService;
import com.songshilong.framework.starter.idempotent.enums.IdempotentSceneEnum;
import com.songshilong.framework.starter.idempotent.enums.IdempotentTypeEnum;
import lombok.RequiredArgsConstructor;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:27
 * @Description: IdempotentExecuteHandlerFactory
 * @Version: 1.0
 */
public final class IdempotentExecuteHandlerFactory {

    public static IdempotentExecuteHandler getHandler(IdempotentSceneEnum scene, IdempotentTypeEnum type) {
        IdempotentExecuteHandler result = null;
        switch (scene) {
            case RESTAPI -> {
                switch (type) {
                    case TOKEN -> result = ApplicationContextHolder.getBean(IdempotentTokenService.class);
                    case PARAMS -> result = ApplicationContextHolder.getBean(IdempotentParamService.class);
                    case SPEL -> result = ApplicationContextHolder.getBean(IdempotentSpELByRestAPIExecuteHandler.class);
                }
            }
            case MQ -> result = ApplicationContextHolder.getBean(IdempotentSpELByMQExecuteHandler.class);
            default -> {}
        }
        return result;
    }

}
