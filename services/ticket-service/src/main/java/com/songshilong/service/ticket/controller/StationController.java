package com.songshilong.service.ticket.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.controller
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  22:26
 * @Description: StationController
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/station")
@Api(value = "车站")
public class StationController {


    @RequestMapping("/all")
    @ApiOperation("查询所有的车站")
    public ResponseData<Void> all() {
        log.info("查询所有的车站");
        return Response.success();
    }
}
