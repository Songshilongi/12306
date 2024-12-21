package com.songshilong.framework.starter.idempotent.toolkit;


import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.toolkit
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:27
 * @Description: LogUtil
 * @Version: 1.0
 */
public final  class LogUtil {
    public static Logger getLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return LoggerFactory.getLogger(signature.getDeclaringType());
    }
}
