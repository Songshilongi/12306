package com.songshilong.service.ticket.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@BelongsProject: 12306-ssl
*@BelongsPackage: com.songshilong.service.ticket.dto.resp
*@Author: Shilong Song
*@CreateTime: 2025-01-11  22:31
*@Description: TicketOrderDetailRespDTO
*@Version: 1.0
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketOrderDetailRespDTO {

    /**
     * 席别类型
     */
    private Integer seatType;

    /**
     * 车厢号
     */
    private String carriageNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;

    /**
     * 车票类型 0：成人 1：儿童 2：学生 3：残疾军人
     */
    private Integer ticketType;

    /**
     * 订单金额
     */
    private Integer amount;
}
