package cn.xiayiye5.xiayiye5library.activity;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.view.ScaleLayout;
import cn.xiayiye5.xiayiye5library.view.ScaleView;

/**
 * @author : xiayiye5
 * @date : 2021/3/26 15:35
 * 类描述 :展示可缩放的页面
 */
public class ScaleActivity extends BaseActivity {

    private ScaleView sv;
    private ScaleLayout sl;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_scale;
    }

    @Override
    protected void initId() {
        sv = findViewById(R.id.sv);
        sl = findViewById(R.id.sl);
    }

    @Override
    protected void loadData() {
        //设置布局可以缩放
        sv.setIsCanTouch(true);
        sl.setIsCanTouch(true);
    }
}
