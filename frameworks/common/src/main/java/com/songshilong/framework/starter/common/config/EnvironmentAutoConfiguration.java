package com.songshilong.framework.starter.common.config;

import com.songshilong.framework.starter.common.toolkit.EnvironmentUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-18  22:09
 * @Description: 环境工具自动配置类
 * @Version: 1.0
 */
public class EnvironmentAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public EnvironmentUtil environmentUtil(Environment environment) {
        return new EnvironmentUtil(environment);
    }

}
