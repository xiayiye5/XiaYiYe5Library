package cn.xiayiye5.xiayiye5library.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.fragment.TakePhotoFragment;
import cn.xiayiye5.xiayiye5library.utils.TakePhotoUtils;

/**
 * @author : xiayiye5
 * @date : 2021/6/9 10:54
 * 类描述 : Activity中拍照
 */
public class TakePhotoActivity extends BaseActivity {

    private ImageView ivTakePhoto;
    private TakePhotoFragment takePhotoFragment;
    public static final int REQUEST_IMAGE_CAPTURE = 10086;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void initId() {
        ivTakePhoto = findViewById(R.id.iv_take_photo);
        Button btTakePhoto = findViewById(R.id.bt_take_photo);
        Button btTakePhotoFragment = findViewById(R.id.bt_take_photo_fragment);
        btTakePhoto.setOnClickListener(v -> TakePhotoUtils.getInstance().dispatchTakePictureIntent(this));
        btTakePhotoFragment.setOnClickListener(v -> takePhotoFragment());
    }

    private void takePhotoFragment() {
        if (null == takePhotoFragment) {
            takePhotoFragment = new TakePhotoFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", "拍照");
        takePhotoFragment.setArguments(bundle);
        //方法一传参数
        Fragment instantiate = TakePhotoFragment.instantiate(this, TakePhotoFragment.class.getName(), bundle);
        //方法二传参数
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_replace, instantiate).addToBackStack(this.getClass().getSimpleName()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.ll_replace, takePhotoFragment).addToBackStack(this.getClass().getSimpleName()).commit();
    }

    @Override
    protected void loadData() {
        // do nothing
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //拍照后的图片回调
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidth = dm.widthPixels;
            Bitmap decodeFile = BitmapFactory.decodeFile(TakePhotoUtils.getInstance().getImgPath());
            int width = decodeFile.getWidth();
            int size = width / screenWidth;
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = false;
            //设置缩放比例
            newOpts.inSampleSize = size * 2;
            Log.e("打印缩放比例", size + "");
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            Bitmap bitmap = BitmapFactory.decodeFile(TakePhotoUtils.getInstance().getImgPath(), newOpts);
            ivTakePhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 10010) {
            boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraAccepted) {
                //权限申请成功
                Toast.makeText(this, "activity页面权限申请成功！", Toast.LENGTH_LONG).show();
                // 调用拍照方法
                TakePhotoUtils.getInstance().dispatchTakePictureIntent(this);
            } else {
                //用户授权拒绝之后，友情提示一下就可以了
                Toast.makeText(this, "activity页面请开启应用拍照权限！", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
