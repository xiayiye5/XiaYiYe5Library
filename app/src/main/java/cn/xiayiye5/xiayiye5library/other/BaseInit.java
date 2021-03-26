package cn.xiayiye5.xiayiye5library.other;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : xiayiye5
 * @date : 2021/3/24 11:48
 * 类描述 :
 */
public class BaseInit {
    int corePoolSize = Runtime.getRuntime().availableProcessors();
    int maximumPoolSize = corePoolSize * 2 + 1;
    long keepAliveTime = 2;
    TimeUnit unit = TimeUnit.HOURS;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(10);
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    RejectedExecutionHandler handle = new ThreadPoolExecutor.AbortPolicy();
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handle);

}
