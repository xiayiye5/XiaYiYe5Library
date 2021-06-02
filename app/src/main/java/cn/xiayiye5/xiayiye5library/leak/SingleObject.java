package cn.xiayiye5.xiayiye5library.leak;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import cn.xiayiye5.xiayiye5library.activity.MemoryLeakActivity;

/**
 * @author : xiayiye5
 * @date : 2021/6/2 17:38
 * 类描述 : 单例内存泄露解决方法
 */
public class SingleObject {
    private static SingleObject singleObject = null;

    private SingleObject(Activity context) {
        WeakReference<Activity> activityWeakReference = new WeakReference<>(context);
        //如果传入的是Application的Context，因为Application的生命周期就是整个应用的生命周期，所以这将没有任何问题。
        //如果传入的是Activity的Context，当这个Context所对应的Activity退出时，由于该Context的引用被单例所持有，
        // 其生命周期等于整个应用程序的生命周期，所以当前Activity退出时它的内存并不会被回收，这就造成泄漏了。
        ((MemoryLeakActivity) activityWeakReference.get()).getBtSingleObj().setText("单例的内存泄露解决了");
        Context mContext = context.getApplicationContext();
    }

    public static SingleObject getInstance(Activity mContext) {
        if (null == singleObject) {
            singleObject = new SingleObject(mContext);
        }
        return singleObject;
    }
}
