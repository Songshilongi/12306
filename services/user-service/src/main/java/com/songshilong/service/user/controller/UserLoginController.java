package com.songshilong.service.user.controller;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import com.songshilong.service.user.dto.request.UserLoginReqDTO;
import com.songshilong.service.user.dto.response.UserLoginRespDTO;
import com.songshilong.service.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.controller
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:50
 * @Description: UserLoginController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/user-service")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;


    @PostMapping("/v1/login")
    public ResponseData<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginRequestDTO) {
        UserLoginRespDTO userLoginResponseDTO = userLoginService.login(userLoginRequestDTO);
        return Response.success(userLoginResponseDTO);
    }

}
