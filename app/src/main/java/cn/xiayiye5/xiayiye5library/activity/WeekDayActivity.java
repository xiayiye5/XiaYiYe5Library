package cn.xiayiye5.xiayiye5library.activity;

import android.util.Log;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.view.WeekDayView;

/**
 * @author : xiayiye5
 * @date : 2021/5/17 16:08
 * 类描述 :
 */
public class WeekDayActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_weekdays;
    }

    @Override
    protected void initId() {
        WeekDayView wk = findViewById(R.id.wk);
        wk.setWeekDay(127);
        wk.addOnWeekDayCheckListener(new WeekDayView.OnWeekDayCheckListener() {
            @Override
            public void selectNum(int num) {
                Log.e("打印十进制", num + "");
            }

            @Override
            public void selectString(String num) {
                Log.e("打印字符串", num);

            }
        });
    }

    @Override
    protected void loadData() {

    }
}
