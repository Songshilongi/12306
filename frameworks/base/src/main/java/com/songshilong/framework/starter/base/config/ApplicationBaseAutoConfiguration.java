package com.songshilong.framework.starter.base.config;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import com.songshilong.framework.starter.base.init.ApplicationContentPostProcessor;
import com.songshilong.framework.starter.base.safe.FastJsonSafeMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base.config
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  14:33
 * @Description: 配置Bean
 * @Version: 1.0
 */
public class ApplicationBaseAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }


    @Bean
    @ConditionalOnMissingBean
    public ApplicationContentPostProcessor applicationContentPostProcessor(ApplicationContext applicationContext) {
        return new ApplicationContentPostProcessor(applicationContext);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "framework.fastjson.safa-mode", havingValue = "true")
    public FastJsonSafeMode fastJsonSafeMode() {
        return new FastJsonSafeMode();
    }



}
