package com.songshilong.service.user.service.chain.register;

import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.convention.exception.ServiceException;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.enums.UserRegisterErrorCodeEnum;
import com.songshilong.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service.chain.register
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  22:02
 * @Description: UserRegisterHasUsernameHandler -- 检查用户名是否重复
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class UserRegisterHasUsernameHandler implements UserRegisterChainHandler<UserRegisterReqDTO>{

    private final UserService userService;


    @Override
    public void handler(UserRegisterReqDTO requestParam) {
        Boolean hasUsername = userService.hasUsername(requestParam.getUsername());
        if (hasUsername) {
            throw new ClientException(UserRegisterErrorCodeEnum.HAS_USERNAME_NOTNULL);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
