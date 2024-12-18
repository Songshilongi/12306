package com.songshilong.framework.starter.common.threadpool.proxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.threadpool.proxy
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  22:56
 * @Description: 拒绝策略代理执行工具类
 * @Version: 1.0
 */
public class RejectedProxyUtil {

    /**
     * 创建拒绝策略的代理对象
     * @param handler 线程池拒绝消费处理器
     * @param rejectCount 拒绝次数
     * @return 处理器代理
     */
    public static RejectedExecutionHandler createProxy(RejectedExecutionHandler handler, AtomicLong rejectCount) {
        // 拒绝消费策略动态代理增强
        return (RejectedExecutionHandler) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                new Class[]{RejectedExecutionHandler.class},
                new RejectedProxyInvocationHandler(handler, rejectCount)
        );
    }
}
