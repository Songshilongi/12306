package com.songshilong.framework.starter.database.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.songshilong.framework.starter.database.handler.CustomIdGenerator;
import com.songshilong.framework.starter.database.handler.MyMetaObjectHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.database.config
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  10:00
 * @Description: MybatisPlusAutoConfiguration
 * @Version: 1.0
 */
public class MybatisPlusAutoConfiguration {



    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @Primary
    public IdentifierGenerator identifierGenerator() {
        return new CustomIdGenerator();
    }


    @Bean
    @ConditionalOnMissingBean
    public MyMetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }

}
