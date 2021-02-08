package cn.xiayiye5.xiayiye5library.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/2/8 14:57
 * 类描述 :
 */
public class MyListAdapter extends XiaYiYeAdapter<String> {
    int[] a = new int[2];
    public final List<Integer> locationList = new ArrayList<>();

    public MyListAdapter(int layoutId, List<String> data) {
        super(layoutId, data);
    }

    @Override
    protected void coverData(XiaYiYeHolder holder, int position) {
        holder.itemView.getLocationOnScreen(a);
        locationList.add(a[0]);
        setText(R.id.tvTestData, "测试数据" + position);
    }
}
