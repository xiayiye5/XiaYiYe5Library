package cn.xiayiye5.xiayiye5library.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 18:51
 * 类描述 :
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化框架
        ARouter.getInstance().inject(this);
        setContentView(getLayoutView());
        initId();
        loadData();
    }


    /**
     * 加载布局的方法
     *
     * @return 返回🔙布局ID
     */
    protected abstract int getLayoutView();

    /**
     * 初始化ID
     */
    protected abstract void initId();

    /**
     * 加载数据
     */
    protected abstract void loadData();
}
