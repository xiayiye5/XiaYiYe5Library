package cn.xiayiye5.xiayiye5library.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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

    public void download(Activity activity, String url, String fileNamePath, String mediaType) {
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
                refreshApi29(activity, fileNamePath);
//                refreshMinApi29(activity, fileNamePath, mediaType);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContentValues getVideoContentValues(File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
     /* localContentValues.put(MediaStore.Video.Media.TITLE, paramFile.getName());
        localContentValues.put(MediaStore.Video.Media.DISPLAY_NAME, paramFile.getName());
        localContentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        localContentValues.put(MediaStore.Video.Media.DATE_TAKEN, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.Video.Media.DATE_MODIFIED, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.Video.Media.DATE_ADDED, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.Video.Media.DATA, paramFile.getAbsolutePath());
        localContentValues.put(MediaStore.Video.Media.SIZE, Long.valueOf(paramFile.length()));*/

        //方法二
        localContentValues.put(MediaStore.MediaColumns.TITLE, paramFile.getName());
        localContentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, paramFile.getName());
        localContentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        localContentValues.put(MediaStore.MediaColumns.DATE_TAKEN, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.MediaColumns.DATE_MODIFIED, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.MediaColumns.DATE_ADDED, Long.valueOf(paramLong));
        localContentValues.put(MediaStore.MediaColumns.DATA, paramFile.getAbsolutePath());
        localContentValues.put(MediaStore.MediaColumns.SIZE, Long.valueOf(paramFile.length()));
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


    /**
     * 刷新图片和视频到相册低版本方法
     *
     * @param activity     当前页面
     * @param fileNamePath 文件路径
     * @param mediaType    刷新的文件类型
     */
    private void refreshMinApi29(Activity activity, String fileNamePath, String mediaType) {
        if (mediaType.contains("image")) {
            File file = new File(fileNamePath);
//            try {
//                //将图片插入到相册
//                MediaStore.Images.Media.insertImage(activity.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
//            } catch (FileNotFoundException e) {
//                Log.i("Exception Msg", Log.getStackTraceString(e));
//            }
//            activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileNamePath)));


            //将图片扫描到相册中显示
            ContentResolver localContentResolver = activity.getContentResolver();
            ContentValues localContentValues = getImageContentValues(file, System.currentTimeMillis());
            localContentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues);
            Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            final Uri localUri = Uri.fromFile(file);
            localIntent.setData(localUri);
            activity.sendBroadcast(localIntent);


        } else {
            //刷新视频到相册方法二 API29以下的老方法，在API29中已弃用！
            ContentResolver localContentResolver = activity.getContentResolver();
            ContentValues localContentValues = getVideoContentValues(new File(fileNamePath), System.currentTimeMillis());
            Uri localUri = localContentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, localContentValues);
            activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri));
        }
    }

    public static ContentValues getImageContentValues(File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("title", paramFile.getName());
        localContentValues.put("_display_name", paramFile.getName());
        localContentValues.put("mime_type", "image/jpeg");
        localContentValues.put("datetaken", Long.valueOf(paramLong));
        localContentValues.put("date_modified", Long.valueOf(paramLong));
        localContentValues.put("date_added", Long.valueOf(paramLong));
        localContentValues.put("orientation", Integer.valueOf(0));
        localContentValues.put("_data", paramFile.getAbsolutePath());
        localContentValues.put("_size", Long.valueOf(paramFile.length()));
        return localContentValues;
    }

    /**
     * 刷新视频到相册方法一 API通用方法包括API29及以上高版本方法
     *
     * @param activity     当前页面
     * @param fileNamePath 文件路径
     */
    private void refreshApi29(Activity activity, String fileNamePath) {
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
    }
}
