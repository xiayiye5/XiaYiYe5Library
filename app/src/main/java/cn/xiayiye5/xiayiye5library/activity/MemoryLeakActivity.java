package cn.xiayiye5.xiayiye5library.activity;

import android.app.Activity;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Constructor;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.leak.LeakHandler;
import cn.xiayiye5.xiayiye5library.leak.SingleObject;

/**
 * @author : xiayiye5
 * @date : 2021/6/2 17:14
 * 类描述 : 知乎内存泄露帖子 @link https://zhuanlan.zhihu.com/p/56961372
 */
public class MemoryLeakActivity extends BaseActivity implements View.OnClickListener {

    private LeakHandler leakHandler;
    private Button btHandler;
    private Button btSingleObj;
    private Button btStatic;
    private Button btThread;
    private static SingleObject singleObject;
    private MyThread myThread;

    public Button getBtHandler() {
        return btHandler;
    }

    public Button getBtSingleObj() {
        return btSingleObj;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_memory_leak;
    }

    @Override
    protected void initId() {
        btHandler = findViewById(R.id.bt_handler);
        btSingleObj = findViewById(R.id.bt_singleObj);
        btStatic = findViewById(R.id.bt_static);
        btThread = findViewById(R.id.bt_thread);
        btHandler.setOnClickListener(this);
        btSingleObj.setOnClickListener(this);
        btStatic.setOnClickListener(this);
        btThread.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        // do nothing
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_handler) {
            //handler的内存泄露
            leakHandler = new LeakHandler(Looper.getMainLooper(), this);
            leakHandler.sendEmptyMessage(0);
        } else if (v.getId() == R.id.bt_singleObj) {
            //单例的内存泄露
            SingleObject.getInstance(this);
        } else if (v.getId() == R.id.bt_static) {
            //通过反射创建对象，static的内存泄露
            try {
                Class<?> aClass = Class.forName("cn.xiayiye5.xiayiye5library.leak.SingleObject");
                Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(Activity.class);
                //使用暴力反射获取私有构造来创建对象
                declaredConstructor.setAccessible(true);
                //此静态对象会一直常驻不会洗哦啊会所以需要在activity销毁的时候置空
                singleObject = (SingleObject) declaredConstructor.newInstance(this);
                Log.e("打印创建的静态对象", singleObject.getClass().getSimpleName());
                if (!TextUtils.isEmpty(singleObject.getClass().getSimpleName())) {
                    btStatic.setText("static的内存泄露解决了");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.bt_thread) {
            //多线程产生的内存泄露
            if (null == myThread) {
                myThread = new MyThread();
            }
            myThread.start();
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != leakHandler) {
            leakHandler.removeCallbacksAndMessages(null);
            leakHandler = null;
        }
        singleObject = null;
        //页面销毁的时候中断线程
        if (null != myThread) {
            myThread.interrupt();
        }
    }
}
