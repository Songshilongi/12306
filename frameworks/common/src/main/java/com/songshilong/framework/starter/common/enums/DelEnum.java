package com.songshilong.framework.starter.common.enums;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  21:48
 * @Description: 删除标记枚举类
 * @Version: 1.0
 */
public enum DelEnum {
    /**
     * 正常状态
     */
    NORMAL(0),
    /**
     * 删除状态
     */
    DELETE(1);


    private final Integer statusCode;


    DelEnum(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "DelEnum{" +
                "statusCode=" + statusCode +
                '}';
    }

    /**
     * Get method
     */
    public Integer code(){
        return this.statusCode;
    }
    /**
     * Get method (String)
     */
    public String strCode(){
        return String.valueOf(this.statusCode);
    }


}
