package com.fiber.common.thread;

import java.util.concurrent.*;

/**
 * @author panyox
 */
public class TaskExecutor {

    private int corePoolSize = 1;
    private int maxPoolSize = 100;
    private int keepAliveSeconds = 60;
    private int queueCapacity = 10000;

    private ThreadPoolExecutor threadPoolExecutor;

    private static TaskExecutor INSTANCE = new TaskExecutor();

    public TaskExecutor() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(queueCapacity);
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, workQueue);
    }

    public static TaskExecutor me() {
        return INSTANCE;
    }

    public void execute(Runnable task) {
        try {
            this.threadPoolExecutor.execute(task);
        } catch (RejectedExecutionException var1) {
            System.out.println(var1);
        }
    }
}
