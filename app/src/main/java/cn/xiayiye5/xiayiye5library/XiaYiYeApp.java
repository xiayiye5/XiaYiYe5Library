package cn.xiayiye5.xiayiye5library;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.xiayiye5.xiayiye5library.utils.ANRWatchDog;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 18:46
 * 类描述 :
 */
public class XiaYiYeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this);
        new ANRWatchDog().start();
    }
}
