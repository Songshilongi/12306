package com.songshilong.framework.starter.convention.exception;

import com.songshilong.framework.starter.convention.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.exception
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:05
 * @Description: 异常
 * @Version: 1.0
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    /**
     * 异常码
     */
    public final String errorCode;
    /**
     * 异常信息
     */
    public final String errorMessage;


    public AbstractException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null)
                .orElse(errorCode.message());
    }



}
