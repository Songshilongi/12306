package com.songshilong.service.user.config;

import com.songshilong.service.user.config.properties.UsernameBloomFilterProperties;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  22:24
 * @Description: BeanConfiguration -- 统一管理自定义的Bean
 * @Version: 1.0
 */
@EnableConfigurationProperties({UsernameBloomFilterProperties.class})
@Configuration
public class BeanConfiguration {


    @Bean
    public RBloomFilter<String> usernameBloomFilter(RedissonClient redissonClient, UsernameBloomFilterProperties usernameBloomFilterProperties) {
        RBloomFilter<String> usernameBloomFilter = redissonClient.getBloomFilter(usernameBloomFilterProperties.getName());
        usernameBloomFilter.tryInit(usernameBloomFilterProperties.getExpectedInterceptors(), usernameBloomFilterProperties.getFalseProbability());
        return usernameBloomFilter;
    }


}
