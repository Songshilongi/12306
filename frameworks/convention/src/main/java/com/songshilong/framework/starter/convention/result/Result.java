package com.songshilong.framework.starter.convention.result;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.result
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:44
 * @Description: 响应结果
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 2468468465846L;

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;


    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }



}
