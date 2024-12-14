package com.songshilong.framework.starter.base;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  15:25
 * @Description: 单例对象容器
 * @Version: 1.0
 */
public final class Singleton {

    private static final ConcurrentHashMap<String, Object> SINGLETON_OBJECT_POOL = new ConcurrentHashMap<>();

    public static <T> T get(String key) {
        Object result = SINGLETON_OBJECT_POOL.get(key);
        return result == null ? null : (T) result;
    }


    public static <T> Object get(String key, Supplier<T> supplier) {
        Object result = SINGLETON_OBJECT_POOL.get(key);
        if (result == null && supplier.get() != null) {
            result = supplier.get();
            SINGLETON_OBJECT_POOL.put(key, result);
        }
        return result == null ? null : (T) result;
    }


    public static void put(Object value) {
        SINGLETON_OBJECT_POOL.put(value.getClass().getName(), value);
    }

    public static void put(String key, Object value) {
        SINGLETON_OBJECT_POOL.put(key, value);
    }


}
