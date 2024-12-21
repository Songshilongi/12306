package com.songshilong.framework.starter.idempotent.annotations;

import com.songshilong.framework.starter.idempotent.enums.IdempotentSceneEnum;
import com.songshilong.framework.starter.idempotent.enums.IdempotentTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.annotations
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:15
 * @Description: Idempotent
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    /**
     * @return 幂等校验失败，返回提示信息
     */
    String message() default "操作频繁，请重试！";
    /**
     * @return 校验场景，默认是RESTAPI
     */
    IdempotentSceneEnum scene() default IdempotentSceneEnum.RESTAPI;
    /**
     * 默认使用Token进行校验
     * @return
     */
    IdempotentTypeEnum type() default IdempotentTypeEnum.PARAMS;
    /**
     * 幂等Key，只有在 {@link Idempotent#type()} 为 {@link IdempotentTypeEnum#SPEL} 时生效
     */
    String key() default "";

    /**
     * 设置防重令牌 Key 前缀，MQ 幂等去重可选设置
     * {@link IdempotentSceneEnum#MQ} and {@link IdempotentTypeEnum#SPEL} 时生效
     */
    String uniqueKeyPrefix() default "";

    /**
     * 设置防重令牌 Key 过期时间，单位秒，默认 1 小时，MQ 幂等去重可选设置
     * {@link IdempotentSceneEnum#MQ} and {@link IdempotentTypeEnum#SPEL} 时生效
     */
    long keyTimeout() default 3600L;






}
