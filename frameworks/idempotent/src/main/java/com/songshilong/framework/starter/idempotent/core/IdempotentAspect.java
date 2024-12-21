package com.songshilong.framework.starter.idempotent.core;

import com.songshilong.framework.starter.idempotent.annotations.Idempotent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:19
 * @Description: IdempotentAspect
 * @Version: 1.0
 */
@Aspect
public final class IdempotentAspect {


    @Pointcut("@annotation(com.songshilong.framework.starter.idempotent.annotations.Idempotent) || @within(com.songshilong.framework.starter.idempotent.annotations.Idempotent)")
    public void pointCut() {

    }


    @Around("pointCut()")
    public Object handler(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Idempotent idempotent = this.getIdempotent(joinPoint);



        Object result = null;





        return result;
    }



    private Idempotent getIdempotent(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        return method.getAnnotation(Idempotent.class);
    }

}
