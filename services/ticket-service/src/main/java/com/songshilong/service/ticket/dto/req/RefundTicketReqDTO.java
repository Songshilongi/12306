package com.songshilong.service.ticket.dto.req;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dto.req
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:33
 * @Description: RefundTicketReqDTO
 * @Version: 1.0
 */
@Data
public class RefundTicketReqDTO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 退款类型 0 部分退款 1 全部退款
     */
    private Integer type;

    /**
     * 部分退款子订单记录id集合
     */
    private List<String> subOrderRecordIdReqList;
}
