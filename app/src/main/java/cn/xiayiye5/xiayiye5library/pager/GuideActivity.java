package cn.xiayiye5.xiayiye5library.pager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
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
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected int getLayoutView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initId() {
        vp = findViewById(R.id.vp);
        pb = findViewById(R.id.pb);
        findViewById(R.id.pb).setOnClickListener(v -> startAnimAction());
    }

    @Override
    protected void loadData() {
        handler.sendMessage(handler.obtainMessage());
        handler.sendMessageDelayed(Message.obtain(), 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 100);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.sendEmptyMessage(0);
                //开启looper循环必须在发送消息之后
                Looper.loop();
                Log.e("打印", "handler");
            }
        }).start();
        HandlerThread.interrupted();
        startAnimAction();
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

    private void startAnimAction() {
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator alphaAnimation1 = ObjectAnimator.ofFloat(pb, View.ALPHA.getName(), 1f, 0f, 0.5f, 1.0f);
        ObjectAnimator alphaAnimation2 = ObjectAnimator.ofFloat(vp, View.TRANSLATION_X.getName(), 0f, 100f, 50f, 0f);
        //回弹插值器
        alphaAnimation2.setInterpolator(new AnticipateOvershootInterpolator(2f));
        animationSet.play(alphaAnimation1).with(alphaAnimation2);
        animationSet.setDuration(1000);
        animationSet.start();
    }
}
