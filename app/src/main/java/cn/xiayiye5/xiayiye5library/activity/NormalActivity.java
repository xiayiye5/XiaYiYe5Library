package cn.xiayiye5.xiayiye5library.activity;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.comment.RouterValue;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:10
 * 类描述 :
 */
@Route(path = RouterValue.NORMAL_PAGE)
public class NormalActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_normal;
    }

    @Override
    protected void initId() {
        Log.e("打印初始化NormalActivity", "id成功");
    }

    @Override
    protected void loadData() {
        Toast.makeText(this, "load成功", Toast.LENGTH_SHORT).show();
        Log.e("打印初始化NormalActivity", "load成功");

    }
}
