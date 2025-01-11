package com.songshilong.service.ticket.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
*@BelongsProject: 12306-ssl
*@BelongsPackage: com.songshilong.service.ticket.dto.resp
*@Author: Shilong Song
*@CreateTime: 2025-01-11  22:31
*@Description: TicketPurchaseRespDTO
*@Version: 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPurchaseRespDTO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 乘车人订单详情
     */
    private List<TicketOrderDetailRespDTO> ticketOrderDetails;
}
