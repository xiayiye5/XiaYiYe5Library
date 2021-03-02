package cn.xiayiye5.xiayiye5library.activity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.bean.ObjName;
import cn.xiayiye5.xiayiye5library.bean.ObjNameSer;
import cn.xiayiye5.xiayiye5library.comment.RouterValue;
import cn.xiayiye5.xiayiye5library.utils.XiaYiYe5Utils;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:10
 * 类描述 :
 */
public class DemoActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initId() {
        Log.e("打印初始化DemoActivity", "id成功");
        //通过反射创建对象
        try {
            Class<?> aClass = Class.forName("cn.xiayiye5.xiayiye5library.utils.XiaYiYe5Utils");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            //暴力反射
            declaredConstructor.setAccessible(true);
            Object o = declaredConstructor.newInstance();
            Log.e("打印对象", o.getClass().getSimpleName());
            if (o instanceof XiaYiYe5Utils) {
                Application newApplication = ((XiaYiYe5Utils) o).getNewApplication();
                Log.e("打印对象", newApplication.getClass().getSimpleName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadData() {
        Log.e("打印初始化DemoActivity", "load成功");
    }

    public void jump(View view) {
        ARouter.getInstance().build(RouterValue.NORMAL_PAGE).navigation();
    }

    public void jumpWithPram(View view) {
        ARouter.getInstance().build(RouterValue.WITH_PARAM_PAGE)
                .withString("name", "长大了")
                .navigation();
    }

    public void jumpWithObject(View view) {
        ObjName data = new ObjName("test", 20, "北京市东直门外大街230号");
//        ARouter.getInstance().build(RouterValue.WITH_PARAM_OBJECT).withObject("objName", data).navigation();
    }

    public void jumpWithSer(View view) {
        ObjNameSer data = new ObjNameSer();
        data.setAddress("北京市东直门外大街239号");
        ARouter.getInstance().build(RouterValue.WITH_PARAM_SER).withSerializable("objNameSer", data).navigation();
    }

    public void goDemo(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
