package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:55
 * @Description: UserLoginRequestDTO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("用户登录请求参数")
public class UserLoginReqDTO {

    /**
     * 用户名
     */
    @NotNull
    @ApiModelProperty("用户名/邮箱/手机号")
    private String usernameOrMailOrPhone;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty("密码")
    private String password;

}
