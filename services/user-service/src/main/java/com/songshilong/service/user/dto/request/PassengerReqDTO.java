package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:24
 * @Description: PassengerReqDTO
 * @Version: 1.0
 */
@Data
@ApiModel("乘车人添加&修改请求参数")
public class PassengerReqDTO {


    /**
     * 乘车人id
     */
    @ApiModelProperty("乘车人id")
    private String id;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 证件类型
     */
    @ApiModelProperty("证件类型")
    private Integer idType;

    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    private String idCard;

    /**
     * 优惠类型
     */
    @ApiModelProperty("优惠类型")
    private Integer discountType;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
}
