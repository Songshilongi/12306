package com.songshilong.service.ticket.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dto.req
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:33
 * @Description: CancelTicketOrderReqDTO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelTicketOrderReqDTO {

    /**
     * 订单号
     */
    private String orderSn;
}
