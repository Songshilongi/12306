package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:23
 * @Description: PassengerRemoveReqDTO
 * @Version: 1.0
 */
@Data
@ApiModel("乘车人移除请求参数")
public class PassengerRemoveReqDTO {

    @ApiModelProperty("乘车人id")
    private String id;
}
