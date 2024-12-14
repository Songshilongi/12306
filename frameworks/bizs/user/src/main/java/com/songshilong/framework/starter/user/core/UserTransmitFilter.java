package com.songshilong.framework.starter.user.core;


import com.songshilong.framework.starter.base.constant.UserConstant;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.user.core
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  20:33
 * @Description: UserTransmitFilter - 过滤请求中的用户信息
 * @Version: 1.0
 */
public class UserTransmitFilter implements Filter  {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String userId = httpServletRequest.getHeader(UserConstant.USER_ID_KEY);
        if (StringUtils.hasText(userId)) {
            String username = httpServletRequest.getHeader(UserConstant.USER_NAME_KEY);
            String realName = httpServletRequest.getHeader(UserConstant.REAL_NAME_KEY);
            if (StringUtils.hasText(username)) {
                username = URLDecoder.decode(username, StandardCharsets.UTF_8);
            }
            if (StringUtils.hasText(realName)) {
                realName = URLDecoder.decode(realName, StandardCharsets.UTF_8);
            }
            String token = httpServletRequest.getHeader(UserConstant.USER_TOKEN_KEY);
            UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                    .userId(userId)
                    .username(username)
                    .realName(realName)
                    .token(token)
                    .build();
            UserContext.setUserInfo(userInfoDTO);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUserInfo();
        }
    }
    /*
    请求进来之后，经过用户信息过滤器拿到用户信息，然后执行下一个过滤器。
    执行完所有的过滤器之后，正常处理请求，结束之后清除该用户的上下文信息
     */
}
