package cn.xiayiye5.xiayiye5library.other;

import android.util.Log;


/**
 * @author : xiayiye5
 * @date : 2021/3/24 11:50
 * 类描述 :
 */
public class CommentInit extends BaseInit {
    private static final CommentInit COMMENT_INIT = new CommentInit();

    private CommentInit() {
    }

    public static CommentInit getInstance() {
        return COMMENT_INIT;
    }

    /**
     * 开启线程池的方法
     */
    public void start(Runnable runnable) {
        Log.e("打印", "线程开启");
        threadPoolExecutor.execute(runnable);
    }
}
