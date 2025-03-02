package com.songshilong.service.ticket.service;

import com.songshilong.service.ticket.dto.req.TicketPageQueryReqDTO;
import com.songshilong.service.ticket.dto.resp.TicketPageQueryRespDTO;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.service
 * @Author: Shilong Song
 * @CreateTime: 2025-01-13  21:56
 * @Description: TicketService
 * @Version: 1.0
 */
public interface TicketService {


    /**
     * 根据条件分页查询车票
     *
     * @param requestParam 分页查询车票请求参数
     * @return 查询车票返回结果
     **/
    TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam);

}
