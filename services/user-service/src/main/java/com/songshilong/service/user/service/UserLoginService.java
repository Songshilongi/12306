package com.songshilong.service.user.service;

import com.songshilong.service.user.dto.request.UserLoginReqDTO;
import com.songshilong.service.user.dto.response.UserLoginRespDTO;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:52
 * @Description: UserLoginService
 * @Version: 1.0
 */
public interface UserLoginService {
    /**
     * 用户登录
     * @param requestParam 用户登录参数 {@link UserLoginReqDTO}
     * @return {@link UserLoginRespDTO}
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);
}
