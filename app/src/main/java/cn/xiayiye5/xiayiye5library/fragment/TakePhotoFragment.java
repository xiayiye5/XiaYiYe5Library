package cn.xiayiye5.xiayiye5library.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.activity.TakePhotoActivity;
import cn.xiayiye5.xiayiye5library.utils.TakePhotoUtils;

import static android.app.Activity.RESULT_OK;

/**
 * @author : xiayiye5
 * @date : 2021/6/9 12:41
 * 类描述 : fragment页面拍照
 */
public class TakePhotoFragment extends Fragment {

    private ImageView ivTakePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_take_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = getArguments().getString("name");
        Log.e("打印传递参数", name);
        Button btTakePhoto = getView().findViewById(R.id.bt_take_photo);
        ivTakePhoto = getView().findViewById(R.id.iv_take_photo);
        btTakePhoto.setOnClickListener(v -> TakePhotoUtils.getInstance().dispatchTakePictureIntent(TakePhotoFragment.this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TakePhotoActivity.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            DisplayMetrics dm = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
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
                Toast.makeText(requireActivity(), "fragment页面权限申请成功！", Toast.LENGTH_LONG).show();
                // 调用拍照方法
                TakePhotoUtils.getInstance().dispatchTakePictureIntent(this);
            } else {
                //用户授权拒绝之后，友情提示一下就可以了
                Toast.makeText(requireActivity(), "fragment页面请开启应用拍照权限！", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
