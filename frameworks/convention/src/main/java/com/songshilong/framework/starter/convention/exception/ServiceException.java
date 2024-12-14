package com.songshilong.framework.starter.convention.exception;

import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.errorcode.ErrorCode;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.exception
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:24
 * @Description: ServiceException
 * @Version: 1.0
 */
public class ServiceException extends AbstractException{

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(ErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ServiceException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }


    public ServiceException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
