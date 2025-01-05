package com.songshilong.framework.starter.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.songshilong.framework.starter.convention.errorcode.BaseErrorCode;
import com.songshilong.framework.starter.convention.exception.AbstractException;
import com.songshilong.framework.starter.convention.result.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.web
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  16:19
 * @Description: GlobalExceptionHandler
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<Void> validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = CollectionUtil.getFirst(bindingResult.getFieldErrors());
        String message = Optional.ofNullable(fieldError)
                .map(FieldError::getDefaultMessage)
                .orElse(StrUtil.EMPTY);
        log.error("[MethodName: {}], RequestRI: [{}], {}", request.getMethod(), parseRequestUrl(request), exception.toString());
        return Response.failure(BaseErrorCode.CLIENT_ERROR.code(), message);
    }

    @ExceptionHandler(value = AbstractException.class)
    public ResponseData<Void> validAbstractException(HttpServletRequest request, AbstractException exception) {
        log.error("[MethodName: {}], RequestRI: [{}], {}", request.getMethod(), parseRequestUrl(request), exception.toString());
        return Response.failure(exception);
    }

    /**
     * 拦截未捕获异常
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseData<Void> defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        log.error("[{}] URI: {}", request.getMethod(), parseRequestUrl(request), throwable);
        return Response.failure();
    }

    private String parseRequestUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        return StringUtils.hasText(queryString) ? requestUrl + '?' + queryString : requestUrl;
    }

}
