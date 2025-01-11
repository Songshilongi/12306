package com.songshilong.service.user.service;

import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.dto.response.UserQueryActualRespDTO;
import com.songshilong.service.user.dto.response.UserQueryRespDTO;
import com.songshilong.service.user.dto.response.UserRegisterRespDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  18:22
 * @Description: UserInfoService
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 注册新用户
     *
     * @param requestParams 注册参数{@link UserRegisterReqDTO}
     * @return {@link UserRegisterRespDTO}
     */
    UserRegisterRespDTO register(UserRegisterReqDTO requestParams);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return true-用户名已经存在 false-用户名不存在
     */
    Boolean hasUsername(String username);

    /**
     * 查询证件注册的次数
     *
     * @param idType 证件类型
     * @param idCard 证件号
     * @return 注册次数
     */
    Long queryUserDeletionCount(@NotNull Integer idType, @NotNull String idCard);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return {@link UserQueryRespDTO}
     */
    UserQueryRespDTO queryByUsername(@NotEmpty String username);

    /**
     * 根据用户名查询用户信息(真实信息)
     * @param username 用户名
     * @return {@link UserQueryActualRespDTO}
     */
    UserQueryActualRespDTO queryActualByUsername(@NotEmpty String username);
}
