package com.songshilong.framework.starter.common.enums;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  21:52
 * @Description: 状态枚举
 * @Version: 1.0
 */
public enum StatusEnum {
    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAIL(1);

    private final Integer statusCode;

    StatusEnum(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer code() {
        return this.statusCode;
    }

    public String strCode() {
        return String.valueOf(this.statusCode);
    }

    @Override
    public String toString() {
        return strCode();
    }

}
