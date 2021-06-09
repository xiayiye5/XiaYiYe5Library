package cn.xiayiye5.xiayiye5library.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.xiayiye5.xiayiye5library.activity.TakePhotoActivity;

/**
 * @author : xiayiye5
 * @date : 2021/6/9 12:52
 * 类描述 :
 */
public class TakePhotoUtils {
    private static final TakePhotoUtils TAKE_PHOTO_UTILS = new TakePhotoUtils();
    private String imgPath;

    private TakePhotoUtils() {
    }

    public static TakePhotoUtils getInstance() {
        return TAKE_PHOTO_UTILS;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void dispatchTakePictureIntent(Activity activity) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA)) {
            //拍照方法
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile(activity);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (photoFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".xiayiye5", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    activity.startActivityForResult(takePictureIntent, TakePhotoActivity.REQUEST_IMAGE_CAPTURE);
                }
            }
        } else {
            //提示用户开户权限   拍照和读写sd卡权限
            String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(activity, perms, 10010);
        }
    }

    public void dispatchTakePictureIntent(Fragment fragment) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(fragment.getContext(), android.Manifest.permission.CAMERA)) {
            //拍照方法
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(fragment.requireActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile(fragment.requireActivity());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (photoFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(fragment.requireActivity(), fragment.requireActivity().getPackageName() + ".xiayiye5", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    //在fragment页面拍照需要使用fragment中的startActivityForResult，不然不会走fragment中的回调
                    fragment.startActivityForResult(takePictureIntent, TakePhotoActivity.REQUEST_IMAGE_CAPTURE);
                }
            }
        } else {
            //提示用户开户权限   拍照和读写sd卡权限
            String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            //在fragment中申请权限时候需要使用fragment中的requestPermissions方法不然不会走fragment中的回调
            fragment.requestPermissions(perms, 10010);
        }
    }

    private File createImageFile(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imgPath = image.getAbsolutePath();
        Log.e("打印图片路径", imgPath);
        return image;
    }
}
