package com.songshilong.framework.starter.idempotent.enums;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:21
 * @Description: TypeEnum
 * @Version: 1.0
 */
public enum IdempotentTypeEnum {
    /**
     * 方法入参
     */
    PARAMS,
    /**
     * Redis Token 或者请求获取唯一token
     */
    TOKEN,
    /**
     * SPEL验证
     */
    SPEL;
}
