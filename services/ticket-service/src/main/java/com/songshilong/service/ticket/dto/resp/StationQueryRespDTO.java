package com.songshilong.service.ticket.dto.resp;

import lombok.Data;

/**
*@BelongsProject: 12306-ssl
*@BelongsPackage: com.songshilong.service.ticket.dto.resp
*@Author: Shilong Song
*@CreateTime: 2025-01-11  22:31
*@Description: StationQueryRespDTO
*@Version: 1.0
*/
@Data
public class StationQueryRespDTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 地区编码
     */
    private String code;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 城市名称
     */
    private String regionName;
}
