package com.songshilong.service.ticket.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
*@BelongsProject: 12306-ssl
*@BelongsPackage: com.songshilong.service.ticket.dto.resp
*@Author: Shilong Song
*@CreateTime: 2025-01-11  22:31
*@Description: TrainStationQueryRespDTO
*@Version: 1.0
*/
@Data
public class TrainStationQueryRespDTO {

    /**
     * 站序
     */
    private String sequence;

    /**
     * 站名
     */
    private String departure;

    /**
     * 到站时间
     */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date arrivalTime;

    /**
     * 出发时间
     */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date departureTime;

    /**
     * 停留时间
     */
    private Integer stopoverTime;
}
