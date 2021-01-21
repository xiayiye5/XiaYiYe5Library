package cn.xiayiye5.xiayiye5library.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/1/21 14:13
 * 类描述 :
 */
public class XiaYiYe5Dialog {
    private static final XiaYiYe5Dialog XIA_YI_YE_5_DIALOG = new XiaYiYe5Dialog();

    private XiaYiYe5Dialog() {
    }

    public static XiaYiYe5Dialog getInstance() {
        return XIA_YI_YE_5_DIALOG;
    }

    public XiaYiYe5Dialog createViewDialog(Activity activity, View view) {
        Button btCancel = view.findViewById(R.id.bt_cancel);
        Button btSure = view.findViewById(R.id.bt_sure);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        AlertDialog dialog = alertDialog.setView(view).create();
        dialog.show();
        btCancel.setOnClickListener(v -> dialog.dismiss());
        btSure.setOnClickListener(v -> dialog.dismiss());
        return this;
    }

    /**
     * 创建单选框
     *
     * @param activity 显示的activity页面
     */
    public void getSimpleDialog(Activity activity) {
        final String[] items = {"单选1", "单选2", "单选3", "单选4"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
        alertBuilder.setTitle("这是单选框");
        alertBuilder.setSingleChoiceItems(items, 0, (dialogInterface, i)
                -> Toast.makeText(activity, items[i], Toast.LENGTH_SHORT).show());
        alertBuilder.setPositiveButton("确定", null);
        alertBuilder.setNegativeButton("取消", null);
        //下面这一步最后显示
        alertBuilder.show();
    }

    /**
     * 创建复选框
     *
     * @param activity 显示的activity页面
     */
    public void getMultiDialog(Activity activity) {
        final String[] items = {"多选1", "多选2", "多选3", "多选4"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
        alertBuilder.setTitle("这是多选框");
        /*
         *第一个参数:弹出框的消息集合，一般为字符串集合
         * 第二个参数：默认被选中的，布尔类数组
         * 第三个参数：勾选事件监听
         */
        alertBuilder.setMultiChoiceItems(items, null, (dialogInterface, i, isChecked) -> {
            if (isChecked) {
                Toast.makeText(activity, "选择" + items[i], Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "取消选择" + items[i], Toast.LENGTH_SHORT).show();
            }
        });
        alertBuilder.setPositiveButton("确定", null);
        alertBuilder.setNegativeButton("取消", null);
        //下面这一步最后显示
        alertBuilder.show();
    }
}
