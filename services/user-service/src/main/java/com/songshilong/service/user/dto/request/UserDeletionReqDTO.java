package com.songshilong.service.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dto.request
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:26
 * @Description: UserDeletionReqDTO
 * @Version: 1.0
 */
@Data
@ApiModel("用户注销请求参数")
public class UserDeletionReqDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
}
