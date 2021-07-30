package cn.xiayiye5.xiayiye5library.activity;

import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.fragment.FragmentOne;

/**
 * @author : xiayiye5
 * @date : 2021/7/30 10:32
 * 类描述 :
 */
public class FragmentReplaceActivity extends BaseActivity {

    private ProgressBar pbCurrent;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_fragment_replace;
    }

    @Override
    protected void initId() {
        pbCurrent = findViewById(R.id.pb_current);
    }

    @Override
    protected void loadData() {
        Fragment fragmentFive = new FragmentOne(this, "第5个页面", null, R.id.fl_replace, 100);
        Fragment fragmentFour = new FragmentOne(this, "第4个页面", fragmentFive, R.id.fl_replace, 80);
        Fragment fragmentThree = new FragmentOne(this, "第3个页面", fragmentFour, R.id.fl_replace, 60);
        Fragment fragmentTwo = new FragmentOne(this, "第2个页面", fragmentThree, R.id.fl_replace, 40);
        Fragment fragment = new FragmentOne(this, "第1个页面", fragmentTwo, R.id.fl_replace, 20);
        replaceFg(fragment);
    }

    public void replaceFg(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_replace, fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
    }

    public void setTopProgress(int progress) {
        pbCurrent.setMax(100);
        pbCurrent.setProgress(progress);
    }

}
