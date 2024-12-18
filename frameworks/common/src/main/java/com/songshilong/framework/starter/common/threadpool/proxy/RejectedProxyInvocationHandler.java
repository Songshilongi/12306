package com.songshilong.framework.starter.common.threadpool.proxy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.threadpool.proxy
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  22:47
 * @Description: 线程池拒绝策略
 * @Version: 1.0
 */
@Slf4j
@AllArgsConstructor
public class RejectedProxyInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;
    /**
     * 拒绝次数
     */
    private final AtomicLong rejectCount;

    /*
     https://blog.csdn.net/yaomingyang/article/details/80981004
     Java动态代理InvocationHandler和Proxy学习笔记
     */


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        this.rejectCount.incrementAndGet();
        try {
            // 执行真实对象的方法
            return method.invoke(target, args);
        } catch (InvocationTargetException exception) {
            throw exception.getCause();
        }
    }
}
