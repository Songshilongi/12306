package com.songshilong.framework.starter.convention.exception;

import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.errorcode.ErrorCode;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.exception
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:11
 * @Description: ClientException 客户端异常
 * @Version: 1.0
 */
public class ClientException extends AbstractException{

    /**
     * 调用<code>ClientException</code>的全参构造器，由于只传入了自定义的异常信息，所以Throwable为null，但是IErrorCode传递他的实现类
     * <code>BaseErrorCode</code>中的用户端错误
     * @param message 指定的异常信息
     */
    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }


    /**
     * 调用<code>ClientException</code>的全参构造器，由于没有传入自定义的异常信息和Throwable对象，所以给null值
     * @param errorCode 异常码和异常信息接口的实现类
     */
    public ClientException(ErrorCode errorCode) {
        this(null, null, errorCode);
    }

    /**
     * 调用<code>ClientException</code>的全参构造器，由于只缺少了Throwable对象，所以给null值，另外两个利用传入的参数即可。
     * @param message 指定的异常信息
     * @param errorCode 异常码和异常信息接口的实现类
     */
    public ClientException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }

    /**
     * 全参构造器，直接调用父类也就是AbstractException的全参构造器
     * @param message 指定的异常信息
     * @param cause 可抛出的异常对象
     * @param errorCode 异常码和异常信息接口的实现类
     */
    public ClientException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
