package cn.xiayiye5.xiayiye5library.utils;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * @author : xiayiye5
 * @date : 2021/1/15 18:20
 * 类描述 : 通过反射获取全局的application
 */
@SuppressLint("PrivateApi")
public class XiaYiYe5Utils {

    private static final XiaYiYe5Utils APPLICATION_UTILS = new XiaYiYe5Utils();

    private XiaYiYe5Utils() {

    }

    public static XiaYiYe5Utils getInstance() {
        return APPLICATION_UTILS;
    }

    private Application currentApplication;
    private static final Application INSTANCE;

    /**
     * 获取全局的application 方法一
     *
     * @return 返回application
     */
    @SuppressLint("PrivateApi")
    public Application getNewApplication() {
        try {
            if (currentApplication == null) {
                currentApplication = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null, (Object[]) null);
            }
            return currentApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        //获取全局application方法二
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null) {
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
            }
        } catch (final Exception e) {
            e.printStackTrace();
            try {
                app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                e.printStackTrace();
            }
        } finally {
            INSTANCE = app;
        }
    }

    public static Application getGlobalApplication() {
        return INSTANCE;
    }
}
