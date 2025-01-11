package com.songshilong.service.ticket.dto.req;

import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dto.req
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:33
 * @Description: RegionStationQueryReqDTO
 * @Version: 1.0
 */
@Data
public class RegionStationQueryReqDTO {

    /**
     * 查询方式
     */
    private Integer queryType;

    /**
     * 名称
     */
    private String name;
}
