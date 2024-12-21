package com.songshilong.framework.starter.log.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.log.annotations
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  14:13
 * @Description: TODO
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLog {

    /**
     * 入参
     */
    boolean input() default true;

    /**
     * 出参
     */
    boolean output() default true;
}
