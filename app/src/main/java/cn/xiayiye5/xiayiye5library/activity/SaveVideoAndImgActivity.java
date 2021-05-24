package cn.xiayiye5.xiayiye5library.activity;

import android.os.Environment;
import android.view.View;

import java.util.Random;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.utils.DownLoadUtils;
import cn.xiayiye5.xiayiye5library.utils.ThreadUtils;

/**
 * @author : xiayiye5
 * @date : 2021/5/21 17:36
 * 类描述 : 下载图片视频刷新到相册的方法
 */
public class SaveVideoAndImgActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_video_and_img;
    }

    @Override
    protected void initId() {

    }

    @Override
    protected void loadData() {

    }

    public void savePicture(View view) {
        //下载图片之前记得开启读取sd卡权限
        String fileNamePath = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + "_" + new Random().nextInt(Integer.MAX_VALUE) + ".jpg";
        ThreadUtils.getInstance().createThread(() -> DownLoadUtils.getInstance().download(SaveVideoAndImgActivity.this, DownLoadUtils.IMG_URL, fileNamePath, "image/jpg"));
//        new Thread(() -> DownLoadUtils.getInstance().download(this, DownLoadUtils.IMG_URL, fileNamePath)).start();
    }

    public void saveVideo(View view) {
        //下载视频之前记得开启读取sd卡权限
        String fileNamePath = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + "_" + new Random().nextInt(Integer.MAX_VALUE) + ".mp4";
        ThreadUtils.getInstance().createThread(() -> DownLoadUtils.getInstance().download(SaveVideoAndImgActivity.this, DownLoadUtils.VIDEO_URL, fileNamePath, "video/mp4"));
//        new Thread(() -> DownLoadUtils.getInstance().download(this, DownLoadUtils.VIDEO_URL, fileNamePath)).start();
    }
}
