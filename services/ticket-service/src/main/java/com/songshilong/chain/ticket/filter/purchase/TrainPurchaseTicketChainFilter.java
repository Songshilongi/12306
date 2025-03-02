package com.songshilong.chain.ticket.filter.purchase;

import com.songshilong.framework.starter.designpattern.chain.AbstractChainHandler;
import com.songshilong.service.ticket.common.enums.TicketChainMarkEnum;
import com.songshilong.service.ticket.dto.req.PurchaseTicketReqDTO;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.chain.ticket.filter.purchase
 * @Author: Shilong Song
 * @CreateTime: 2025-01-13  22:09
 * @Description: TrainPurchaseTicketChainFilter - 购票责任链标识
 * @Version: 1.0
 */
public interface TrainPurchaseTicketChainFilter<T extends PurchaseTicketReqDTO> extends AbstractChainHandler<PurchaseTicketReqDTO> {
    @Override
    default String mark() {
        return TicketChainMarkEnum.TRAIN_PURCHASE_TICKET_FILTER.name();
    }
}
