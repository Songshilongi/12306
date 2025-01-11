package com.songshilong.service.user.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.dto.response.UserQueryActualRespDTO;
import com.songshilong.service.user.dto.response.UserQueryRespDTO;
import com.songshilong.service.user.dto.response.UserRegisterRespDTO;
import com.songshilong.service.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/query")
    @ApiOperation("查询用户信息")
    public ResponseData<UserQueryRespDTO> queryByUsername(@RequestParam @NotEmpty String username) {
        UserQueryRespDTO responseData =  userService.queryByUsername(username);
        return Response.success(responseData);
    }

    @GetMapping("/actual/query")
    @ApiOperation("查询用户信息")
    public ResponseData<UserQueryActualRespDTO> queryActualByUsername(@RequestParam @NotEmpty String username) {
        UserQueryActualRespDTO responseData =  userService.queryActualByUsername(username);
        return Response.success(responseData);
    }



}
