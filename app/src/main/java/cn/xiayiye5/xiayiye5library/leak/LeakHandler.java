package cn.xiayiye5.xiayiye5library.leak;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.xiayiye5.xiayiye5library.activity.MemoryLeakActivity;

/**
 * @author : xiayiye5
 * @date : 2021/6/2 17:30
 * 类描述 : 静态内部类默认不持有外部类的引用，所以改成静态内部类即可。同时，可以采用弱引用来持有Activity的引用。
 * 1.首先，非静态的Handler类会默认持有外部类的引用，如Activity等。
 * 2.然后，还未处理完的消息（Message）中会持有Handler的引用。
 * 3.还未处理完的消息会处于消息队列中，即消息队列MessageQueue会持有Message的引用。
 * 4.消息队列MessageQueue位于Looper中，Looper的生命周期跟应用一致。
 */
public class LeakHandler extends Handler {
    private final MemoryLeakActivity memoryLeakActivity;

    public LeakHandler(@NonNull Looper looper, Activity memoryLeak) {
        super(looper);
        //将传递过来的activity用WeakReference进行包装可以解决非静态类一直持有activity的引用
        this.memoryLeakActivity = (MemoryLeakActivity) new WeakReference<>(memoryLeak).get();
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        memoryLeakActivity.getBtHandler().setText("Handler内存泄露解决了");
    }
}
