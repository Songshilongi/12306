package com.songshilong.framework.starter.convention.exception;

import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.errorcode.ErrorCode;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.exception
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:19
 * @Description: RemoteException
 * @Version: 1.0
 */
public class RemoteException extends AbstractException{

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(ErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public RemoteException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
