package com.songshilong.service.ticket.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import com.songshilong.service.ticket.dto.req.TicketPageQueryReqDTO;
import com.songshilong.service.ticket.dto.resp.TicketPageQueryRespDTO;
import com.songshilong.service.ticket.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.controller
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:05
 * @Description: TicketController
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ticket")
@Api(tags = "订票接口")
public class TicketController {

    private final TicketService ticketService;


    @GetMapping("/query")
    @ApiOperation(value = "查询所有的票")
    public ResponseData<TicketPageQueryRespDTO> query(TicketPageQueryReqDTO requestParam) {
        return Response.success(ticketService.pageListTicketQueryV1(requestParam));
    }

}
