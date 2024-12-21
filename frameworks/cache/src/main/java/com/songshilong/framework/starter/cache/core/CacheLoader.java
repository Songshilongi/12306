package com.songshilong.framework.starter.cache.core;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.cache.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:42
 * @Description: CacheLoader
 * @Version: 1.0
 */
@FunctionalInterface
public interface CacheLoader<T> {

    /**
     * 加载缓存
     */
    T load();
}
