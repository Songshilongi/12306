package com.songshilong.framework.starter.idempotent.config;

import com.songshilong.framework.starter.base.config.ApplicationBaseAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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


}
