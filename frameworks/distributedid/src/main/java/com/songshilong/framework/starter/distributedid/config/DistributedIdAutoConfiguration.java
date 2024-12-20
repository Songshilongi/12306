package com.songshilong.framework.starter.distributedid.config;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import com.songshilong.framework.starter.distributedid.core.snowflake.LocalRedisWorkIdChoose;
import com.songshilong.framework.starter.distributedid.core.snowflake.RandomWorkIdChoose;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  23:14
 * @Description: DistributedIdAutoConfiguration
 * @Version: 1.0
 */
@Import(ApplicationContextHolder.class)
public class DistributedIdAutoConfiguration {

    @Bean
    @ConditionalOnProperty("spring.data.redis.host")
    public LocalRedisWorkIdChoose localRedisWorkIdChoose() {
        return new LocalRedisWorkIdChoose();
    }


    @Bean
    @ConditionalOnMissingBean(LocalRedisWorkIdChoose.class)
    public RandomWorkIdChoose randomWorkIdChoose() {
        return new RandomWorkIdChoose();
    }


}
