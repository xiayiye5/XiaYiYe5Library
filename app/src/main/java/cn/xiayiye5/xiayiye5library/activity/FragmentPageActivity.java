package cn.xiayiye5.xiayiye5library.activity;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.fragment.HomeFragment;

/**
 * @author : xiayiye5
 * @date : 2021/4/12 15:08
 * 类描述 :
 */
public class FragmentPageActivity extends BaseActivity {

    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    int num = 0;
    private ProgressBar pb;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_fragment_page;
    }

    @Override
    protected void initId() {
        pb = findViewById(R.id.pb);
    }

    @Override
    protected void loadData() {
        fragment1 = HomeFragment.getInstance("1");
        fragment2 = HomeFragment.getInstance("2");
        fragment3 = HomeFragment.getInstance("3");
        fragment4 = HomeFragment.getInstance("4");
        pb.setMax(100);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_add, fragment1, "1");
        fragmentTransaction.add(R.id.fl_add, fragment2, "2");
        fragmentTransaction.add(R.id.fl_add, fragment3, "3");
        fragmentTransaction.add(R.id.fl_add, fragment4, "4");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        hideAll();
    }

    public void hideAll() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragment1);
        fragmentTransaction.hide(fragment2);
        fragmentTransaction.hide(fragment3);
        fragmentTransaction.hide(fragment4);
        fragmentTransaction.commit();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void showNext(View view) {
        hideAll();
        if (num % 4 == 0) {
            pb.setProgress(25);
            showFragment(fragment1);
        } else if (num % 4 == 1) {
            pb.setProgress(50);
            showFragment(fragment2);
        } else if (num % 4 == 2) {
            pb.setProgress(75);
            showFragment(fragment3);
        } else if (num % 4 == 3) {
            pb.setProgress(100);
            showFragment(fragment4);
        }
        num++;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "left count:" + getSupportFragmentManager().getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
        //取出我们返回栈存在Fragment的个数
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
        //取出返回栈保存的Fragment,这里会从栈顶开始出栈
    }
}


