package com.songshilong.service.user.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.controller
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:22
 * @Description: UserInfoController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/user-service")
@RequiredArgsConstructor
@Api("用户信息相关接口")
public class UserInfoController {



    @PostMapping("/register")
    @ApiOperation("注册用户")
    public ResponseData<Void> register() {


        return Response.success();
    }
}
