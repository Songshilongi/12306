package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:26
 * @Description: UserRegisterReqDTO
 * @Version: 1.0
 */
@Data
@ApiModel("用户注册请求参数")
public class UserRegisterReqDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotNull
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotNull
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    @NotNull
    private String realName;

    /**
     * 证件类型
     */
    @ApiModelProperty("证件类型")
    @NotNull
    private Integer idType;

    /**
     * 证件号
     */
    @ApiModelProperty("证件号")
    @NotNull
    private String idCard;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotNull
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotNull
    private String mail;

    /**
     * 旅客类型
     */
    @ApiModelProperty("旅客类型")
    private Integer userType;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer verifyState;

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

    /**
     * 国家/地区
     */
    @ApiModelProperty("国家/地区")
    private String region;

    /**
     * 固定电话
     */
    @ApiModelProperty("固定电话")
    private String telephone;
}
