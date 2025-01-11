package com.songshilong.service.ticket.dto.req;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dto.req
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:33
 * @Description: TicketOrderItemQueryReqDTO
 * @Version: 1.0
 */
@Data
public class TicketOrderItemQueryReqDTO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 子订单记录id
     */
    private List<String> orderItemRecordIds;
}
