package cn.xiayiye5.xiayiye5library.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import cn.xiayiye5.xiayiye5library.inter.MainThreadCallBack;

/**
 * @author : xiayiye5
 * @date : 2021/1/18 10:42
 * 类描述 : 在任意页面进行主线程开启
 */
public class MainThread {
    private MainThreadCallBack mainThreadCallBack;
    private static final MainThread MAIN_THREAD = new MainThread();

    private MainThread() {
    }

    public static MainThread getInstance() {
        return MAIN_THREAD;
    }

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //回调主线程
            mainThreadCallBack.onFinish();
        }
    };

    /**
     * 此方法可以在activity fragment 任意页面进行主线程开启
     *
     * @param mainThreadCallBack 回调
     */
    public void open(MainThreadCallBack mainThreadCallBack) {
        this.mainThreadCallBack = mainThreadCallBack;
        //发送消息，回调到主线程
        handler.sendEmptyMessage(0);
    }
}
