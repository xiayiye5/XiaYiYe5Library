package cn.xiayiye5.xiayiye5library.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author : xiayiye5
 * @date : 2021/4/13 10:17
 * 类描述 :
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;

    public GuidePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
