package cn.xiayiye5.xiayiye5library.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.comment.RouterValue;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:33
 * 类描述 :
 */
@Route(path = RouterValue.WITH_PARAM_PAGE)
public class WithParamActivity extends BaseActivity {
    @Autowired
    String name;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_with_param;
    }

    @Override
    protected void initId() {
        Log.e("打印初始化WithParamActivity", name);
        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(String.format("携带参数后跳转的页面%s", name));
    }

    @Override
    protected void loadData() {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
