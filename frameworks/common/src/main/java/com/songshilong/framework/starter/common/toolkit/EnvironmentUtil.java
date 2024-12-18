package com.songshilong.framework.starter.common.toolkit;

import com.songshilong.framework.starter.common.enums.EnvironmentEnum;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jaxb.runtime.v2.runtime.output.Encoded;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.toolkit
 * @Author: Shilong Song
 * @CreateTime: 2024-12-18  21:40
 * @Description: 检测当前系统环境的工具类
 * @Version: 1.0
 */


public class EnvironmentUtil {

    private static Environment environment;

    public EnvironmentUtil(Environment environment) {
        EnvironmentUtil.environment = environment;
    }

    /**
     * 判断当前是否是开发环境
     * @return true-开发环境
     */
    public static Boolean isDevEnvironment() {
        return env(EnvironmentEnum.DEV.desc());
    }

    /**
     * 判断当前是否是测试环境
     * @return true-测试环境
     */
    public static Boolean isBetaEnvironment() {
        return env(EnvironmentEnum.BETA.desc());
    }
    /**
     * 判断当前是否是生产环境
     * @return true-生产环境
     */
    public static Boolean isProdEnvironment() {
        return env(EnvironmentEnum.PROD.desc());
    }


    private static boolean env(String comparedEnv) {
        if (StringUtils.isBlank(comparedEnv)) {
            return false;
        }
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if (StringUtils.equals(profile, comparedEnv)) {
                return true;
            }
        }
        return false;
    }
}
