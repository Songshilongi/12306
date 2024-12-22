package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:28
 * @Description: UserUpdateReqDTO
 * @Version: 1.0
 */
@Data
@ApiModel("用户修改请求参数")
public class UserUpdateReqDTO {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String mail;

    /**
     * 旅客类型
     */
    @ApiModelProperty("旅客类型")
    private Integer userType;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String postCode;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
}
