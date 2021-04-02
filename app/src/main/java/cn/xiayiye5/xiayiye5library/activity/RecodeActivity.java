package cn.xiayiye5.xiayiye5library.activity;


import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/4/2 17:06
 * 类描述 : 通过adb命令实现录屏功能失败
 */
@Deprecated
public class RecodeActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_recode;
    }

    @Override
    protected void initId() {

    }

    @Override
    protected void loadData() {

    }

    public void startRecode(View view) {
        //执行 cmd 命令
        try {
//            Process process = Runtime.getRuntime().exec("adb shell screenrecord --size 720x1280 --bit-rate 6000000 --time-limit 30 /sdcard/test.mp4");
            Process process = Runtime.getRuntime().exec("screenrecord --size 720x1280 --bit-rate 6000000 --time-limit 30 /sdcard/test.mp4");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = bufferedReader.readLine();
            Log.e("打印视频录制流", s + "==");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
