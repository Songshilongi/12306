package com.songshilong.framework.starter.web;

import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.exception.AbstractException;
import com.songshilong.framework.starter.convention.result.Result;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.web
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  16:11
 * @Description: TODO
 * @Version: 1.0
 */
public final class ResponseData {

    /**
     * 构造成功响应
     */
    public static Result<Void> success() {
        return Result.<Void>builder()
                .code(Result.SUCCESS_CODE)
                .build();
    }

    /**
     * 构造成功响应
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(Result.SUCCESS_CODE)
                .data(data)
                .build();
    }

    /**
     * 默认的失败信息
     */
    public static Result<Void> failure() {
        return Result.<Void>builder()
                .code(BaseErrorCode.SERVICE_ERROR.code())
                .message(BaseErrorCode.SERVICE_ERROR.message())
                .build();
    }

    /**
     * 根据异常构建信息
     * @param abstractException {@link AbstractException}
     */
    public static Result<Void> failure(AbstractException abstractException) {
        return Result.<Void>builder()
                .code(abstractException.errorCode)
                .message(abstractException.errorMessage)
                .build();
    }


    /**
     * 手动构造失败信息
     */
    public static Result<Void> failure(String errorCode, String errorMessage) {
        return Result.<Void>builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }




}
