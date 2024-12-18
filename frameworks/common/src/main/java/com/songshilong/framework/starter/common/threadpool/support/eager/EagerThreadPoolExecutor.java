package com.songshilong.framework.starter.common.threadpool.support.eager;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.threadpool.support.eager
 * @Author: Shilong Song
 * @CreateTime: 2024-12-17  22:12
 * @Description: 快速消费线程池
 * @Version: 1.0
 */
public class EagerThreadPoolExecutor extends ThreadPoolExecutor {


    public EagerThreadPoolExecutor(int corePoolSize,
                                   int maximumPoolSize,
                                   long keepAliveTime,
                                   TimeUnit unit,
                                   BlockingQueue<Runnable> workQueue,
                                   ThreadFactory threadFactory,
                                   RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 已经提交的任务数量（待处理）
     */
    private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

    /**
     * @return 已经提交的任务数量（待处理）
     */
    public int getSubmittedTaskCount() {
        return this.submittedTaskCount.get();
    }

    /**
     * 任务消费成功之后，对已提交、待处理的任务数量减一
     * @param r the runnable that has completed
     * @param t the exception that caused termination, or null if
     * execution completed normally
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        this.submittedTaskCount.decrementAndGet();
    }

    /**
     * 消费任务的时候，待处理的任务数量加 1。如果线程池满了被拒绝消费，那么尝试往阻塞队列面里面添加
     * @param command the task to execute
     */
    @Override
    public void execute(Runnable command) {
        this.submittedTaskCount.incrementAndGet();
        try {
            super.execute(command);
        } catch (RejectedExecutionException ex) {
            // 拒绝消费，往消费队列里面添加元素
            TaskQueue taskQueue = (TaskQueue) super.getQueue();
            try {
                if (taskQueue.retryOffer(command, 0, TimeUnit.MILLISECONDS)) {
                    this.submittedTaskCount.decrementAndGet();
                }
            } catch (InterruptedException e) {
                this.submittedTaskCount.decrementAndGet();
                throw new RejectedExecutionException(e);
            }
        } catch (Exception e) {
            // 消费失败，任务数量 - 1
            this.submittedTaskCount.decrementAndGet();
        }
    }
}
