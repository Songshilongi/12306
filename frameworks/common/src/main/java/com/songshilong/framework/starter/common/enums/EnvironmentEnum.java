package com.songshilong.framework.starter.common.enums;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-18  21:44
 * @Description: 当前环境
 * @Version: 1.0
 */
public enum EnvironmentEnum {


    DEV("dev"),
    BETA("beta"),
    PROD("prod");


    private final String desc;


    EnvironmentEnum(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }

    @Override
    public String toString() {
        return "EnvironmentEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
