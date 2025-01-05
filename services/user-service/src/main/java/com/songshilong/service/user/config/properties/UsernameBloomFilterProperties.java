package com.songshilong.service.user.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.config.properties
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  22:26
 * @Description: UsernameBloomFilterProperties -- 用户名布隆过滤器
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = UsernameBloomFilterProperties.PREFIX)
@Component
public class UsernameBloomFilterProperties {

    public static final String PREFIX = "frame.cache.redis.bloom-filter.user-register";

    /**
     * 布隆过滤器的名字
     */
    private String name = "user_register_cache_penetration_bloom_filter";
    /**
     * 每个元素预期的插入数量
     */
    private Long expectedInterceptors = 64L;
    /**
     *误判率
     */
    private Double falseProbability = 0.03D;




}
