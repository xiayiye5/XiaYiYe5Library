package cn.xiayiye5.xiayiye5library.activity;

import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;


import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    private LinearLayout ll;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initId() {
        ll = findViewById(R.id.ll);
        Log.e("打印初始化DemoActivity", "id成功");
        //通过反射创建对象
        try {
            Class<?> aClass = Class.forName("cn.xiayiye5.xiayiye5library.utils.XiaYiYe5Utils");
            //如果有public类型的空构造时，可以直接使用class对象.newInstance()创建对象
            //Object o1 = aClass.newInstance();
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            //暴力反射
            declaredConstructor.setAccessible(true);
            Object o = declaredConstructor.newInstance();
            Log.e("打印对象", o.getClass().getSimpleName());
            if (o instanceof XiaYiYe5Utils) {
                Application newApplication = ((XiaYiYe5Utils) o).getNewApplication();
                Log.e("打印对象", newApplication.getClass().getSimpleName());
            }
            //获取方法
            Method getNewApplication = aClass.getMethod("getNewApplication");
            //执行getNewApplication方法,返回一个application对象
            Object invoke = getNewApplication.invoke(o);
            Log.e("打印对象application", invoke.getClass().getSimpleName());

            //获取所有成员变量包括私有的
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //设置暴力反射
                declaredField.setAccessible(true);
                Log.e("打印所有成员变量", declaredField.getName() + " = " + declaredField.get(o));
                //declaredField.set(o, null);
                Log.e("打印所有成员变量-", declaredField.get(o) + "");
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
        //增加打印app整个过程的handler日志
        Looper.getMainLooper().setMessageLogging(new Printer() {
            @Override
            public void println(String x) {
                Log.e("打印全局handler消息", x);
            }
        });
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

    public void saveImgAndVideo(View view) {
        startActivity(new Intent(this, SaveVideoAndImgActivity.class));
    }

    public void saveImg(View view) {
        saveImage29(createBitmap(ll));
    }

    public Bitmap createBitmap(View v) {
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

    /**
     * API29以下方法
     *
     * @param toBitmap 图片
     */
    private void saveImage(Bitmap toBitmap) {
        String insertImage = MediaStore.Images.Media.insertImage(getContentResolver(), toBitmap, "壁纸", "搜索猫相关图片后保存的图片");
        if (!TextUtils.isEmpty(insertImage)) {
            Toast.makeText(this, "图片保存成功!" + insertImage, Toast.LENGTH_SHORT).show();
            Log.e("打印保存路径", insertImage + "-");
        }
    }

    /**
     * API29及以上方法
     *
     * @param toBitmap 图片
     */
    private void saveImage29(Bitmap toBitmap) {
        //开始一个新的进程执行保存图片的操作
        Uri insertUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        //使用use可以自动关闭流
        try {
            OutputStream outputStream = getContentResolver().openOutputStream(insertUri, "rw");
            if (toBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)) {
                Log.e("保存成功", "success");
            } else {
                Log.e("保存失败", "fail");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
