package com.songshilong.service.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.response
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:29
 * @Description: PassengerActualRespDTO
 * @Version: 1.0
 */
@Data
@ApiModel("乘车人真实返回参数，不包含脱敏信息")
public class PassengerActualRespDTO {

    /**
     * 乘车人id
     */
    @ApiModelProperty("乘车人id")
    private String id;

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

    /**
     * 添加日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("添加日期")
    private Date createDate;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer verifyStatus;
}
