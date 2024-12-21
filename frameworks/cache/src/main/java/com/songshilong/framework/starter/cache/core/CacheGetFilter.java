package com.songshilong.framework.starter.cache.core;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.cache.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:43
 * @Description: TODO
 * @Version: 1.0
 */
@FunctionalInterface
public interface CacheGetFilter<T> {

    /**
     * 缓存过滤
     *
     * @param param 输出参数
     * @return {@code true} 如果输入参数匹配，否则 {@link Boolean#TRUE}
     */
    boolean filter(T param);
}
