package com.songshilong.service.user.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.response
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:36
 * @Description: UserRegisterRespDTO
 * @Version: 1.0
 */
@Data
@ApiModel("用户注册返回参数")
public class UserRegisterRespDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;


}
