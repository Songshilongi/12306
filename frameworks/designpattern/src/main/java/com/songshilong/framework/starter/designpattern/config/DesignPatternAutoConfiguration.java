package com.songshilong.framework.starter.designpattern.config;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import com.songshilong.framework.starter.base.config.ApplicationBaseAutoConfiguration;
import com.songshilong.framework.starter.designpattern.chain.AbstractChainContext;
import com.songshilong.framework.starter.designpattern.straregy.AbstractStrategyChoose;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.designpattern.config
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  21:31
 * @Description: 设计模式组件库
 * @Version: 1.0
 */
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)
public class DesignPatternAutoConfiguration {

    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }

    @Bean
    public AbstractStrategyChoose abstractStrategyChoose() {
        return new AbstractStrategyChoose();
    }


}
