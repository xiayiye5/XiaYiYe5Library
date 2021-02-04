package cn.xiayiye5.xiayiye5library.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.bean.ObjNameSer;
import cn.xiayiye5.xiayiye5library.comment.RouterValue;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:35
 * 类描述 :
 */
@Route(path = RouterValue.WITH_PARAM_SER)
public class WithSerActivity extends BaseActivity {
    @Autowired
    ObjNameSer objName;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_with_object;
    }

    @Override
    protected void initId() {
        TextView tvObject = findViewById(R.id.tvObject);
        tvObject.setText(String.format("携带对象跳转页面%s", objName.getAddress()));
    }

    @Override
    protected void loadData() {

    }
}
