package com.songshilong.framework.starter.idempotent.core;

import com.songshilong.framework.starter.idempotent.annotations.Idempotent;
import com.songshilong.framework.starter.idempotent.enums.IdempotentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:33
 * @Description: IdempotentParamWrapper
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdempotentParamWrapper {

    /**
     * 幂等注解
     */
    private Idempotent idempotent;

    /**
     * AOP 处理连接点
     */
    private ProceedingJoinPoint joinPoint;

    /**
     * 锁标识，{@link IdempotentTypeEnum#PARAMS}
     */
    private String lockKey;

}
