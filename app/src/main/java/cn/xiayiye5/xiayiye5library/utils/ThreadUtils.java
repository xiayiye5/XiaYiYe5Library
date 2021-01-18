package cn.xiayiye5.xiayiye5library.utils;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : xiayiye5
 * @date : 2021/1/18 10:12
 * 类描述 : 线程池相关,不建议使用java包装好的线程池工具类Executors
 */
public class ThreadUtils {
    /**
     * 线程池相关参数,请点击查看{@link android.os.AsyncTask}
     */
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAXI_MUM_POOL_SIZE = CORE_POOL_SIZE * 2 + 1;
    private static final long KEEP_ALIVE_TIME = 10;
    private static final TimeUnit UNIT = TimeUnit.HOURS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingDeque<>(10);
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();
    private static final RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.AbortPolicy();
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXI_MUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, WORK_QUEUE, THREAD_FACTORY, HANDLER);

    private ThreadUtils() {
    }

    private static final ThreadUtils THREAD_UTILS = new ThreadUtils();

    public static ThreadUtils getInstance() {
        return THREAD_UTILS;
    }

    /**
     * 创建线程池
     *
     * @param runnable runnable接口
     */
    public void createThread(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }

    /**
     * 移除线程池
     *
     * @param runnable runnable接口
     */
    public void removeThread(Runnable runnable) {
        THREAD_POOL_EXECUTOR.remove(runnable);
    }
}
