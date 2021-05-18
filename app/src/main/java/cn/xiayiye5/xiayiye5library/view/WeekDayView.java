package cn.xiayiye5.xiayiye5library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/5/17 16:17
 * 类描述 :
 */
public class WeekDayView extends LinearLayout {

    private CheckBox cb7;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    String defaultWeek = "0000000";
    private final String[] weekArray = new String[]{"0", "0", "0", "0", "0", "0", "0"};

    public WeekDayView(Context context) {
        this(context, null);
    }

    public WeekDayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekDayView);
        String defaultSelect = typedArray.getString(R.styleable.WeekDayView_default_select);
        //判断格式是否正确
        if (defaultSelect.length() != 7) {
            return;
        }
        if (!TextUtils.isEmpty(defaultSelect)) {
            defaultWeek = defaultSelect;
            for (int i = 0; i < defaultWeek.toCharArray().length; i++) {
                weekArray[i] = String.valueOf(defaultWeek.toCharArray()[i]);
            }
        }
        typedArray.recycle();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_weekday, this, false);
        cb7 = view.findViewById(R.id.cb7);
        cb1 = view.findViewById(R.id.cb1);
        cb2 = view.findViewById(R.id.cb2);
        cb3 = view.findViewById(R.id.cb3);
        cb4 = view.findViewById(R.id.cb4);
        cb5 = view.findViewById(R.id.cb5);
        cb6 = view.findViewById(R.id.cb6);
        //设置星期
        setWeekDay(defaultWeek);
        initListener();
        addView(view);
    }

    private void initListener() {
        cb7.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 7));
        cb1.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 1));
        cb2.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 2));
        cb3.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 3));
        cb4.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 4));
        cb5.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 5));
        cb6.setOnCheckedChangeListener((buttonView, isChecked) -> getSelectData(isChecked, 6));
    }

    private void getSelectData(boolean isChecked, int index) {
        weekArray[7 - index] = isChecked ? "1" : "0";
        StringBuilder data = new StringBuilder();
        for (String s : weekArray) {
            data.append(s);
        }
        Log.e("打印数组", data.toString());
        if (null == onWeekDayCheckListener) {
            return;
        }
        onWeekDayCheckListener.selectNum(Integer.parseInt(data.toString(), 2));
        onWeekDayCheckListener.selectString(data.toString());
    }

    private void setWeekFormat(String week) {
        ArrayList<Character> integers = new ArrayList<>();
        for (int j = week.length() - 1; j >= 0; j--) {
            char c = week.toCharArray()[j];
            integers.add(c);
        }
        for (int k = 0; k < integers.size(); k++) {
            if (k == 0) {
                cb1.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 1) {
                cb2.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 2) {
                cb3.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 3) {
                cb4.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 4) {
                cb5.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 5) {
                cb6.setChecked("1".equals(String.valueOf(integers.get(k))));
            } else if (k == 6) {
                cb7.setChecked("1".equals(String.valueOf(integers.get(k))));
            }
        }
    }

    /**
     * 例如7表示周一 周二 周三 十进制转成二进制为111
     *
     * @param num 十进制数字
     */
    public void setWeekDay(int num) {
        String week = Integer.toBinaryString(num);
        setWeekFormat(week);
    }

    /**
     * 传递二进制数字
     *
     * @param week "1101"表示周一 周三 周四
     */
    public void setWeekDay(String week) {
        setWeekFormat(week);
    }

    public interface OnWeekDayCheckListener {
        /**
         * 获取十进制数字
         *
         * @param num 十进制
         */
        void selectNum(int num);

        /**
         * 获取String类型
         *
         * @param num 返回字符串
         */
        void selectString(String num);
    }

    OnWeekDayCheckListener onWeekDayCheckListener;

    public void addOnWeekDayCheckListener(OnWeekDayCheckListener onWeekDayCheckListener) {
        this.onWeekDayCheckListener = onWeekDayCheckListener;
    }
}
