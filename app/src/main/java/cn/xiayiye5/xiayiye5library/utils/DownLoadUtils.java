package cn.xiayiye5.xiayiye5library.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : xiayiye5
 * @date : 2021/5/21 17:45
 * 类描述 :
 */
public class DownLoadUtils {
    public static final String IMG_URL = "https://ae01.alicdn.com/kf/Ua227945b506241af975a9b0a16d6df3bA.jpg";
    public static final String VIDEO_URL = "https://www.w3school.com.cn/example/html5/mov_bbb.mp4";

    private DownLoadUtils() {
    }

    public static DownLoadUtils getInstance() {
        return SingleObject.DOWN_LOAD_UTILS;
    }

    private static class SingleObject {
        private static final DownLoadUtils DOWN_LOAD_UTILS = new DownLoadUtils();
    }

    public void download(Activity activity, String url, String fileNamePath) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(30_000);
            urlConnection.setReadTimeout(30_000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(fileNamePath);
                readFile5(inputStream, fileOutputStream);
                //刷新相册，mineTypes为null的话让系统自己根据文件后缀判断文件类型
                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, null, (path, uri) -> Log.e("资源刷新成功路径为", path));
                //代表只刷新视频格式为mp4类型其它格式视频文件不刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"video/mp4"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
                //代表刷新视频文件，只要是视频都刷新根据当前Android系统支持哪些视频格式进行刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"video/*"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
                //代表只刷新图片格式为jpg的文件到相册中
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"image/jpg"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
                //代表刷新图片到相册只要是图片就会刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"image/*"}, (path, uri) -> Log.e("资源刷新成功路径为", path));

                //下面是API29以下的老方法，在API29已弃用！
//                ContentResolver localContentResolver = activity.getContentResolver();
//                ContentValues localContentValues = getVideoContentValues(new File(fileNamePath), System.currentTimeMillis());
//                Uri localUri = localContentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, localContentValues);
//                activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri));

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContentValues getVideoContentValues(File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put(MediaStore.Video.Media.TITLE, paramFile.getName());
        localContentValues.put("_display_name", paramFile.getName());
        localContentValues.put("mime_type", "video/mp4");
        localContentValues.put("datetaken", Long.valueOf(paramLong));
        localContentValues.put("date_modified", Long.valueOf(paramLong));
        localContentValues.put("date_added", Long.valueOf(paramLong));
        localContentValues.put("_data", paramFile.getAbsolutePath());
        localContentValues.put("_size", Long.valueOf(paramFile.length()));
        return localContentValues;
    }

    /**
     * 读写方式五
     * 高效字节流方式一个字节一个字节的读写
     */
    private void readFile5(InputStream fileInputStream, FileOutputStream fileOutputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //代表读取到的数据的底层int值
            int ch = 0;
            while ((ch = bufferedInputStream.read()) != -1) {
                Log.e("下载资源", ch + "");
                bufferedOutputStream.write(ch);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
