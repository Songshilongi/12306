package com.songshilong.framework.starter.common.threadpool.build;

import com.songshilong.framework.starter.common.toolkit.Assert;
import com.songshilong.framework.starter.designpattern.builder.Builder;

import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.threadpool.build
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  23:02
 * @Description: 自定义线程池builder，构建一个 ThreadPoolExecutor
 * @Version: 1.0
 */
public final class ThreadPoolBuilder implements Builder<ThreadPoolExecutor> {

    /**
     * 核心线程数 - 动态该计算
     */
    private int corePoolSize = this.calculatePoolSize();

    /**
     * @return 根据计算机性能动态生成的核心线程数
     */
    private int calculatePoolSize() {
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        return new BigDecimal(cpuCoreNum).divide(new BigDecimal("0.2")).intValue();
    }

    /**
     * 最大线程数 = 核心线程数 *  1.5
     */
    private int maximumPoolSize = corePoolSize + (corePoolSize >> 1);

    /**
     * 存活时间
     */
    private long keepAliveTime = 30000L;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * 阻塞队列
     */
    private BlockingQueue workQueue = new LinkedBlockingQueue(4096);

    /**
     * 拒绝策略
     */
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 是否是守护线程
     */
    private boolean isDaemon = false;

    /**
     * 线程名字前缀
     */
    private String threadNamePrefix;

    /**
     * 线程工厂，该接口有一个 Thread newThread() 的方法，决定线程池里面的线程如何创建。
     */
    private ThreadFactory threadFactory;


    public ThreadPoolBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ThreadPoolBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ThreadPoolBuilder maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        if (maximumPoolSize < this.corePoolSize) {
            this.corePoolSize = maximumPoolSize;
        }
        return this;
    }

    public ThreadPoolBuilder threadFactory(String threadNamePrefix, Boolean isDaemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.isDaemon = isDaemon;
        return this;
    }

    public ThreadPoolBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ThreadPoolBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        return this;
    }

    public ThreadPoolBuilder rejected(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }

    public ThreadPoolBuilder workQueue(BlockingQueue workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public static ThreadPoolBuilder builder() {
        return new ThreadPoolBuilder();
    }


    @Override
    public ThreadPoolExecutor build() {
        if (threadFactory ==  null) {
            Assert.notEmpty(threadNamePrefix, "线程池前缀不能为空");
            threadFactory = ThreadFactoryBuilder.builder().prefix(threadNamePrefix).daemon(isDaemon).build();
        }

        ThreadPoolExecutor threadPoolExecutor;
        try {
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    timeUnit,
                    workQueue,
                    threadFactory,
                    rejectedExecutionHandler);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Error creating thread pool parameter.", ex);
        }
        return threadPoolExecutor;
    }



}
