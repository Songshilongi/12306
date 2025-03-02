package com.songshilong.chain.ticket.filter.refund;

import com.songshilong.framework.starter.designpattern.chain.AbstractChainHandler;
import com.songshilong.service.ticket.common.enums.TicketChainMarkEnum;
import com.songshilong.service.ticket.dto.req.RefundTicketReqDTO;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.chain.ticket.filter.refund
 * @Author: Shilong Song
 * @CreateTime: 2025-01-13  22:08
 * @Description: TrainRefundTicketChainFilter - 退款的责任链标识
 * @Version: 1.0
 */
public interface TrainRefundTicketChainFilter<T extends RefundTicketReqDTO> extends AbstractChainHandler<RefundTicketReqDTO> {

    @Override
    default String mark() {
        return TicketChainMarkEnum.TRAIN_REFUND_TICKET_FILTER.name();
    }
}
