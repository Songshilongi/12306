package com.songshilong.framework.starter.cache.core;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.cache.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:44
 * @Description: CacheGetIfAbsent
 * @Version: 1.0
 */
@FunctionalInterface
public interface CacheGetIfAbsent<T> {

    /**
     * 如果查询结果为空，执行逻辑
     */
    void execute(T param);
}
