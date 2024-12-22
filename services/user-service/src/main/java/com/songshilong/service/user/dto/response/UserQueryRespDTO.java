package com.songshilong.service.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.songshilong.service.user.serialize.IdCardDesensitizationSerializer;
import com.songshilong.service.user.serialize.PhoneDesensitizationSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.response
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:35
 * @Description: UserQueryRespDTO
 * @Version: 1.0
 */
@Data
@ApiModel("用户查询返回参数")
public class UserQueryRespDTO {

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
     * 国家/地区
     */
    @ApiModelProperty("国家/地区")
    private String region;

    /**
     * 证件类型
     */
    @ApiModelProperty("证件类型")
    private Integer idType;

    /**
     * 证件号
     */
    @JsonSerialize(using = IdCardDesensitizationSerializer.class)
    @ApiModelProperty("证件号")
    private String idCard;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 固定电话
     */
    @ApiModelProperty("固定电话")
    private String telephone;

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
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer verifyStatus;

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
