package com.songshilong.framework.starter.base.safe;

import org.springframework.beans.factory.InitializingBean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base.safe
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  15:03
 * @Description: 配置 fastJson的安全模式，即关闭autoType，防止遇到序列化攻击
 * @Version: 1.0
 */
public class FastJsonSafeMode implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("fastjson.parser.safeMode", "true");
    }
}


/*
InitializingBean接口：
    InitializingBean 是 Spring 框架中的一个接口，它提供了一个方法 afterPropertiesSet()，
    这个方法会在所有属性设置完成后由 Spring 容器自动调用。
    使用 InitializingBean 接口可以确保在 Bean 初始化过程的最后阶段执行某些特定的初始化逻辑。
    这是 Spring 提供的一种方式来让开发者定义 Bean 的初始化行为。
 */