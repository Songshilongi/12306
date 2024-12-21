package com.songshilong.framework.starter.idempotent.config;

import com.songshilong.framework.starter.cache.config.RedisDistributedProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:14
 * @Description: IdempotentProperties
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = IdempotentProperties.PREFIX)
public class IdempotentProperties {
    public static final String PREFIX = "framework.idempotent.token";
    /**
     * Token 幂等 Key 前缀
     */
    private String prefix;

    /**
     * Token 申请后过期时间
     * 单位默认毫秒 {@link TimeUnit#MILLISECONDS}
     * 随着分布式缓存过期时间单位 {@link RedisDistributedProperties#valueTimeout} 而变化
     */
    private Long timeout;
}
