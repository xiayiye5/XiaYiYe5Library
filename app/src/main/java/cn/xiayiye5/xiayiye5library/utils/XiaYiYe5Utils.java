package cn.xiayiye5.xiayiye5library.utils;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * @author : xiayiye5
 * @date : 2021/1/15 18:20
 * 类描述 : 通过反射获取全局的application
 */
public class XiaYiYe5Utils {

    private static final XiaYiYe5Utils APPLICATION_UTILS = new XiaYiYe5Utils();

    private XiaYiYe5Utils() {

    }

    public static XiaYiYe5Utils getInstance() {
        return APPLICATION_UTILS;
    }

    private Application currentApplication;

    /**
     * 获取全局的application
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
}
