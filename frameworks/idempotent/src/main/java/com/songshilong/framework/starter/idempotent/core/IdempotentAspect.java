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
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        Idempotent idempotent = this.getIdempotent(joinPoint);
        IdempotentExecuteHandler instance = IdempotentExecuteHandlerFactory.getHandler(idempotent.scene(), idempotent.type());
        Object result = null;
        try {
            // 先校验幂等
            instance.execute(joinPoint, idempotent);
            // 执行原方法
            result = joinPoint.proceed();
            // 幂等后置处理
            instance.postProcessing();
            /*
            如果中间都没有报错，说明没有问题，否则抛出异常
             */
        }
        catch (RepeatConsumptionException repeatConsumptionException) {
            if (!repeatConsumptionException.getError()) {
                return null;
            }
            throw repeatConsumptionException;
        }
        catch (Throwable throwable) {
            instance.exceptionProcessing();
            throw throwable;
        } finally {
            IdempotentContext.clean();
        }
        return result;
    }



    private Idempotent getIdempotent(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        return method.getAnnotation(Idempotent.class);
    }

}
