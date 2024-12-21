package com.songshilong.framework.starter.log.config;

import com.songshilong.framework.starter.log.core.AspectLogCore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.log.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  14:12
 * @Description: 日志
 * @Version: 1.0
 */
public class AspectLogAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public AspectLogCore aspectLogCore() {
        return new AspectLogCore();
    }

}
