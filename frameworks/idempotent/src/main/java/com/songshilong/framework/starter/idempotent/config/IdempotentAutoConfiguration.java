package com.songshilong.framework.starter.idempotent.config;

import com.songshilong.framework.starter.base.config.ApplicationBaseAutoConfiguration;
import com.songshilong.framework.starter.cache.DistributedCache;
import com.songshilong.framework.starter.idempotent.core.IdempotentAspect;
import com.songshilong.framework.starter.idempotent.core.params.IdempotentParamExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.params.IdempotentParamService;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELByMQExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELByRestAPIExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.spel.IdempotentSpELService;
import com.songshilong.framework.starter.idempotent.core.token.IdempotentTokenExecuteHandler;
import com.songshilong.framework.starter.idempotent.core.token.IdempotentTokenService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:24
 * @Description: IdempotentAutoConfiguration
 * @Version: 1.0
 */
@EnableConfigurationProperties(IdempotentProperties.class)
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)
public class IdempotentAutoConfiguration {


    /**
     * 幂等切面
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentAspect idempotentAspect() {
        return new IdempotentAspect();
    }

    /**
     * Token 方式幂等实现，基于 RestAPI 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentTokenService idempotentTokenExecuteHandler(DistributedCache distributedCache,
                                                                IdempotentProperties idempotentProperties) {
        return new IdempotentTokenExecuteHandler(distributedCache, idempotentProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IdempotentParamService idempotentParamExecuteHandler(RedissonClient redissonClient) {
        return new IdempotentParamExecuteHandler(redissonClient);
    }

    /**
     * SpEL 方式幂等实现，基于 RestAPI 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentSpELByRestAPIExecuteHandler idempotentSpElByRestApiExecuteHandler(RedissonClient redissonClient) {
        return new IdempotentSpELByRestAPIExecuteHandler(redissonClient);
    }

    /**
     * SpEL 方式幂等实现，基于 MQ 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentSpELByMQExecuteHandler idempotentSpElByMqExecuteHandler(DistributedCache distributedCache) {
        return new IdempotentSpELByMQExecuteHandler(distributedCache);
    }





}
