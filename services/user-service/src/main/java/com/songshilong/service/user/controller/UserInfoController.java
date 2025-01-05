package com.songshilong.service.user.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.dto.response.UserRegisterRespDTO;
import com.songshilong.service.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final UserService userService;



    @PostMapping("/register")
    @ApiOperation("注册用户")
    public ResponseData<UserRegisterRespDTO> register(@RequestBody @Valid UserRegisterReqDTO requestParams) {
        UserRegisterRespDTO responseData = userService.register(requestParams);
        return Response.success(responseData);
    }



}
