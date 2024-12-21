package com.songshilong.framework.starter.idempotent.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

import java.util.Map;
import java.util.Objects;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.core.token
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  20:55
 * @Description: IdempotentAspect
 * @Version: 1.0
 */

public class IdempotentContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return CONTEXT.get();
    }

    public static Object getKey(String key) {
        Map<String, Object> context = get();
        if (CollUtil.isEmpty(context)) {
            return null;
        }
        return context.get(key);
    }

    public static String getString(String key) {
        Object object = getKey(key);
        if (Objects.nonNull(object)) {
            return object.toString();
        }
        return null;
    }

    public static void put(String key, Object value) {
        Map<String, Object> context = get();
        if (CollUtil.isEmpty(context)) {
            context = MapUtil.newHashMap();
        }
        context.put(key, value);
    }

    public static void put(Map<String, Object> needAdded) {
        Map<String, Object> context = get();
        if (CollUtil.isNotEmpty(context)) {
            context.putAll(needAdded);
        }
        CONTEXT.set(needAdded);
    }

    public static void clean(){
        CONTEXT.remove();
    }
}
