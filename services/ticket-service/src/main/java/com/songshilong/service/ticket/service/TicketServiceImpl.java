package com.songshilong.service.ticket.service;

import com.songshilong.service.ticket.dto.req.TicketPageQueryReqDTO;
import com.songshilong.service.ticket.dto.resp.TicketPageQueryRespDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.service
 * @Author: Shilong Song
 * @CreateTime: 2025-01-13  21:56
 * @Description: TicketServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService{

    @Override
    public TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam) {
        return null;
    }
}
