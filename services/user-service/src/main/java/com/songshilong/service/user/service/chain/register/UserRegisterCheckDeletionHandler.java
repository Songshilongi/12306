package com.songshilong.service.user.service.chain.register;

import com.songshilong.framework.starter.convention.exception.ClientException;
import com.songshilong.framework.starter.convention.exception.ServiceException;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.enums.UserRegisterErrorCodeEnum;
import com.songshilong.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Queue;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service.chain.register
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  22:03
 * @Description: UserRegisterCheckDeletionHandler -- 检查是否多次注册
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class UserRegisterCheckDeletionHandler implements UserRegisterChainHandler<UserRegisterReqDTO> {
    private final UserService userService;


    @Override
    public void handler(UserRegisterReqDTO requestParam) {
        Long deletionCount = userService.queryUserDeletionCount(requestParam.getIdType(), requestParam.getIdCard());
        if (deletionCount > 5) {
            throw new ClientException(UserRegisterErrorCodeEnum.ID_CARD_REPEAT_REGISTER);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
