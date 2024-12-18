package com.songshilong.framework.starter.common.threadpool.support.eager;

import lombok.Setter;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.threadpool.support.eager
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  22:24
 * @Description: 快速消费任务队列
 * @Version: 1.0
 */
@Setter
public class TaskQueue<R extends Runnable> extends LinkedBlockingQueue<Runnable> {
    /**
     * 快速消费线程池
     */

    private EagerThreadPoolExecutor eagerThreadPoolExecutor;


    public TaskQueue(int capacity) {
        super(capacity);
    }

    /**
     * 往消费线程池中加入任务
     * @param runnable the element to add
     */
    @Override
    public boolean offer(Runnable runnable) {
        // 获取当前线程池的中的线程数量
        int currentThreadPoolSize = this.eagerThreadPoolExecutor.getPoolSize();

        // 已提交的任务数量小于线程池中的任务数量，提提交到阻塞队列，通过线程池控制进行消费
        if (this.eagerThreadPoolExecutor.getSubmittedTaskCount() < currentThreadPoolSize) {
            return super.offer(runnable);
        }
        // 已提交的任务数量大于当前线程数，且当前的线程数量小于最大线程数（包含非核心线程），返回false，线程池会创建非核心线程
        if (currentThreadPoolSize < this.eagerThreadPoolExecutor.getMaximumPoolSize()) {
            return false;
        }
        // 已提交的任务数量大于当前线程数，且当前的线程数量大于等于最大线程数，直接加入等待队列，让线程池决定是否消费
        return super.offer(runnable);
    }

    /**
     * 在一定时间内尝试像阻塞队列中添加任务
     * @param runnable 需要消费的任务
     * @param timeout 重试时间
     * @param unit 重试时间的单位
     * @return 添加成功-true
     * @throws InterruptedException 中断异常
     */
    public boolean retryOffer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
        if (this.eagerThreadPoolExecutor.isShutdown()) {
            throw new RejectedExecutionException("快速消费线程池已关闭！");
        }
        return super.offer(runnable, timeout, unit);
    }

}
