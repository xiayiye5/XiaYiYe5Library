package cn.xiayiye5.xiayiye5library.pager;

import android.util.Log;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.activity.BaseActivity;

/**
 * @author : xiayiye5
 * @date : 2021/4/13 10:06
 * 类描述 :
 */
public class GuideActivity extends BaseActivity implements CurrentPage {

    private ViewPager vp;
    private ProgressBar pb;
    private int currentPosition = 0;
    private final List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initId() {
        vp = findViewById(R.id.vp);
        pb = findViewById(R.id.pb);
    }

    @Override
    protected void loadData() {
        fragmentList.add(OneFragment.getInstance(this, 1));
        fragmentList.add(TwoFragment.getInstance(this, 2));
        fragmentList.add(ThreeFragment.getInstance(this, 3));
        fragmentList.add(FourFragment.getInstance(this, 4));
        fragmentList.add(FiveFragment.getInstance(this, 5));
        fragmentList.add(SixFragment.getInstance(this, 6));
        fragmentList.add(SevenFragment.getInstance(this, 7));
        fragmentList.add(EgihtFragment.getInstance(this, 8));
        fragmentList.add(NineFragment.getInstance(this, 9));
        fragmentList.add(TenFragment.getInstance(this, 10));
        fragmentList.add(ElevenFragment.getInstance(this, 11));
        pb.setMax(fragmentList.size());
        vp.setAdapter(new GuidePagerAdapter(getSupportFragmentManager(), fragmentList));
        initListener();
    }

    private void initListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置进度条
                pb.setProgress(position + 1);
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        currentPosition--;
        Log.e("打印position", currentPosition + "");
        if (currentPosition >= 0) {
            vp.setCurrentItem(currentPosition);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setCurrentPage(int position) {
        vp.setCurrentItem(position);
    }
}
