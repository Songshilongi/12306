package com.songshilong.framework.starter.common.toolkit;

import lombok.SneakyThrows;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.toolkit
 * @Author: Shilong Song
 * @CreateTime: 2024-12-18  22:19
 * @Description: 线程工具类
 * @Version: 1.0
 */
public final class ThreadUtil {

    /**
     * 让当前线程睡眠指定的时间（毫秒）
     * @param millis 多少ms
     */
    @SneakyThrows(value = InterruptedException.class)
    public static void sleep(long millis) {
        Thread.sleep(millis);
    }

}
