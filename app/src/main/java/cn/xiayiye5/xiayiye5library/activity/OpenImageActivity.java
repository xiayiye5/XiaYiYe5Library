package cn.xiayiye5.xiayiye5library.activity;

import android.net.Uri;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/7/29 17:18
 * 类描述 : 显示图片
 */
public class OpenImageActivity extends BaseActivity {

    private TextView tvImgBack;
    private ImageView ivShowImg;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_open_image;
    }

    @Override
    protected void initId() {
        tvImgBack = findViewById(R.id.tv_img_back);
        ivShowImg = findViewById(R.id.iv_show_img);
    }

    @Override
    protected void loadData() {
        Parcelable imgUri = getIntent().getParcelableExtra("img_uri");
        tvImgBack.setOnClickListener(v -> finish());
        ivShowImg.setImageURI((Uri) imgUri);
    }
}
