package com.songshilong.framework.starter.idempotent.core.token;

import com.songshilong.framework.starter.convention.result.ResponseData;
import com.songshilong.framework.starter.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.token
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  21:28
 * @Description: IdempotentTokenController -- 提供接口给用户请求唯一token
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
public class IdempotentTokenController {
    private final IdempotentTokenService idempotentTokenService;



    @GetMapping("/token")
    public ResponseData<String> getToken() {
        String token = idempotentTokenService.createToken();
        return Response.success(token);
    }

}
