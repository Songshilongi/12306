package com.songshilong.framework.starter.log.core;

import cn.hutool.core.date.SystemClock;
import com.songshilong.framework.starter.log.annotations.AspectLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.MultipartFilter;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.log.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  14:17
 * @Description: AspectLogCore
 * @Version: 1.0
 */
@Slf4j
@Aspect
public class AspectLogCore {


    @Pointcut("@annotation(com.songshilong.framework.starter.log.annotations.AspectLog) || @within(com.songshilong.framework.starter.log.annotations.AspectLog)")
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public void printLog(ProceedingJoinPoint joinPoint) {
        long startTime = SystemClock.now();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object result = null;
        try {
            result = joinPoint.proceed();
            Method declaredMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getMethod().getParameterTypes());
            AspectLog logAnnotation = declaredMethod.getAnnotation(AspectLog.class);
            if (null != logAnnotation) {
                LogInfoDTO logInfoDTO = new LogInfoDTO();
                logInfoDTO.setStartTime(String.valueOf(startTime));
                if (logAnnotation.input()) {
                    logInfoDTO.setInputParams(this.buildMethodParams(joinPoint));
                }
                if (logAnnotation.output()) {
                    logInfoDTO.setOutputParams(result);
                }

                // 拿到当前方法所属的类的 Logger 对象 SLF4J
                Logger log = LoggerFactory.getLogger(signature.getDeclaringType());
                String methodName = "";
                String requestURI = "";
                try {
                    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    methodName = servletRequestAttributes.getRequest().getMethod();
                    requestURI = servletRequestAttributes.getRequest().getRequestURI();
                } catch (Exception ignore) {

                }
                log.info("LogInfoDTO: {}, methodName: {}, requestURI: {}", logInfoDTO, methodName, requestURI);
            }
        } catch (Throwable e) {
            throw new RuntimeException("AOP生成日志异常，请检查！");
        }
    }


    /**
     * 构造输入参数
     *
     * @param joinPoint
     * @return
     */
    private Object[] buildMethodParams(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] inputParams = new Object[args.length];
        for (int i = 0; i < inputParams.length; i++) {
            Object obj = args[i];
            if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse) {
                continue;
            } else if (obj instanceof byte[]) {
                inputParams[i] = "byte[]";
            } else if (obj instanceof MultipartFilter) {
                inputParams[i] = "file";
            } else {
                inputParams[i] = obj;
            }
        }
        return inputParams;
    }
}
