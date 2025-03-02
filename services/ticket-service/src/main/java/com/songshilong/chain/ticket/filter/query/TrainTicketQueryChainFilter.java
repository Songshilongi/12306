package com.songshilong.chain.ticket.filter.query;

import com.songshilong.framework.starter.designpattern.chain.AbstractChainHandler;
import com.songshilong.service.ticket.common.enums.TicketChainMarkEnum;
import com.songshilong.service.ticket.dto.req.TicketPageQueryReqDTO;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.chain.ticket.filter.query
 * @Author: Shilong Song
 * @CreateTime: 2025-01-13  22:06
 * @Description: TrainTicketQueryChainFilter - 查询所有的车票责任链标识
 * @Version: 1.0
 */
public interface TrainTicketQueryChainFilter<T extends TicketPageQueryReqDTO> extends AbstractChainHandler<TicketPageQueryReqDTO> {
    @Override
    default String mark() {
        return TicketChainMarkEnum.TRAIN_QUERY_FILTER.name();
    }
}
