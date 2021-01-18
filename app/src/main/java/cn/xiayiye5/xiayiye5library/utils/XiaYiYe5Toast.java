package cn.xiayiye5.xiayiye5library.utils;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/1/15 18:23
 * 类描述 : 吐司相关工具类
 */
public class XiaYiYe5Toast {
    Toast mToast;
    private static final XiaYiYe5Toast XIA_YI_YE_5_TOAST = new XiaYiYe5Toast();

    private XiaYiYe5Toast() {
    }

    public static XiaYiYe5Toast getInstance() {
        return XIA_YI_YE_5_TOAST;
    }

    public void showText(String text) {
        showText(text, Toast.LENGTH_LONG);
    }

    public void showText(String text, int duration) {
        Application newApplication = XiaYiYe5Utils.getInstance().getNewApplication();
        showText(newApplication, text, duration);
    }

    public void showText(Context context, String text, int duration) {
        if (null == mToast) {
            mToast = new Toast(context);
            View toastView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
            mToast.setGravity(Gravity.BOTTOM, 0, DisplayUtils.dp2px(45));
            mToast.setView(toastView);
            mToast.setDuration(duration == 0 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            LinearLayout relativeLayout = mToast.getView().findViewById(R.id.ll_parent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            relativeLayout.setLayoutParams(layoutParams);
            TextView tvContent = relativeLayout.findViewById(R.id.tv_content);
            tvContent.setText(text);
            tvContent.setLayoutParams(tvContent.getLayoutParams());
            relativeLayout.measure(0, 0);
        }
        mToast.show();
    }

}
