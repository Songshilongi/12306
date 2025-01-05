package com.songshilong.service.user.service.chain.register;

import com.songshilong.framework.starter.designpattern.chain.AbstractChainHandler;
import com.songshilong.service.user.dto.request.UserRegisterReqDTO;
import com.songshilong.service.user.enums.UserChainMarkEnum;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.service.chain.register
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  21:44
 * @Description: 用户注册责任链
 * @Version: 1.0
 */
public interface UserRegisterChainHandler<T extends UserRegisterReqDTO> extends AbstractChainHandler<UserRegisterReqDTO> {

    @Override
    default String mark(){
        return UserChainMarkEnum.USER_REGISTER_FILTER.name();
    }

}
