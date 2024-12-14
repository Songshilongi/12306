package com.songshilong.framework.starter.user.config;

import com.songshilong.framework.starter.base.constant.FilterOrderConstant;
import com.songshilong.framework.starter.user.core.UserInfoDTO;
import com.songshilong.framework.starter.user.core.UserTransmitFilter;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.user.config
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  21:00
 * @Description: UserAutoConfiguration
 * @Version: 1.0
 */
@ConditionalOnWebApplication
public class UserAutoConfiguration {

    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter() {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        // 设置过滤器为 UserTransmitFilter
        registration.setFilter(new UserTransmitFilter());
        // 设置过滤器将会拦截所有的 URL 模式（即所有的请求）。
        registration.addUrlPatterns("/*");
        // 设置过滤器的执行顺序，USER_TRANSMIT_FILTER_ORDER = 100 确定过滤器的 优先级
        registration.setOrder(FilterOrderConstant.USER_TRANSMIT_FILTER_ORDER);
        // 返回配置好的用户信息传递过滤器
        return registration;
    }


}
