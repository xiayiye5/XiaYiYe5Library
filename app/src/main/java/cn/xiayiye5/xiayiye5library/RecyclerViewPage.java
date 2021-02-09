package cn.xiayiye5.xiayiye5library;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import cn.xiayiye5.xiayiye5library.adapter.MyListAdapter;

/**
 * @author : xiayiye5
 * @date : 2021/1/26 18:14
 * 类描述 : 测试回退版本v1.0.1
 */
public class RecyclerViewPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView rvList = findViewById(R.id.rvList);
        View viewLine = findViewById(R.id.view_line);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
//        MyAdapter myAdapter = new MyAdapter();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(String.valueOf(i));
        }
        MyListAdapter myAdapter = new MyListAdapter(R.layout.activity_recyclerview_item, data);
        rvList.setAdapter(myAdapter);
        int[] a = new int[2];
        //获取中间竖线在屏幕的绝对坐标
        viewLine.getLocationOnScreen(a);
        //滚动到中间位置
        rvList.scrollToPosition(9);
        //设置缓存数量
        rvList.setItemViewCacheSize(myAdapter.getItemCount());
        //设置滑动监听
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    for (int i = 0; i < myAdapter.locationList.size(); i++) {
                        int b = myAdapter.locationList.get(i);
//                        RecyclerView.ViewHolder viewHolder = rvList.findViewHolderForAdapterPosition(i);
//                        int[] b = new int[2];
//                        viewHolder.itemView.getLocationOnScreen(b);
                        if (a[0] >= b && a[0] <= (b + viewLine.getWidth())) {
                            //判断竖线是否在每个item上
                            Toast.makeText(RecyclerViewPage.this, "中了！", Toast.LENGTH_SHORT).show();
                            Log.e("打印", "中了");
                        }
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        File externalFilesDir1 = getExternalFilesDir(Environment.DIRECTORY_ALARMS);
        File externalFilesDir2 = getExternalFilesDir(Environment.DIRECTORY_AUDIOBOOKS);
        File externalFilesDir3 = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File externalFilesDir4 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File externalFilesDir5 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File externalFilesDir6 = getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File externalFilesDir7 = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File externalFilesDir8 = getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS);
        File externalFilesDir9 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File externalFilesDir10 = getExternalFilesDir(Environment.DIRECTORY_PODCASTS);
        File externalFilesDir11 = getExternalFilesDir(Environment.DIRECTORY_RINGTONES);
        File externalFilesDir12 = getExternalFilesDir(Environment.DIRECTORY_SCREENSHOTS);
        File externalFilesDir13 = getExternalFilesDir(Environment.DIRECTORY_RINGTONES);
        File externalFilesDir14 = getExternalFilesDir(Environment.MEDIA_BAD_REMOVAL);
        File externalFilesDir15 = getExternalFilesDir(Environment.MEDIA_CHECKING);
        File externalFilesDir16 = getExternalFilesDir(Environment.MEDIA_EJECTING);
        File externalFilesDir17 = getExternalFilesDir(Environment.MEDIA_MOUNTED);
        File externalFilesDir18 = getExternalFilesDir(Environment.MEDIA_MOUNTED_READ_ONLY);
        File externalFilesDir19 = getExternalFilesDir(Environment.MEDIA_NOFS);
        File externalFilesDir20 = getExternalFilesDir(Environment.MEDIA_REMOVED);
        File externalFilesDir21 = getExternalFilesDir(Environment.MEDIA_SHARED);
        File externalFilesDir22 = getExternalFilesDir(Environment.MEDIA_UNKNOWN);
        File externalFilesDir23 = getExternalFilesDir(Environment.MEDIA_UNMOUNTABLE);
        File externalFilesDir24 = getExternalFilesDir(Environment.MEDIA_UNMOUNTED);
        Log.e("打印路径", externalFilesDir1.getAbsolutePath()
                + "\n" + externalFilesDir1.getAbsolutePath()
                + "\n" + externalFilesDir1.getAbsolutePath()
                + "\n" + externalFilesDir2.getAbsolutePath()
                + "\n" + externalFilesDir3.getAbsolutePath()
                + "\n" + externalFilesDir4.getAbsolutePath()
                + "\n" + externalFilesDir5.getAbsolutePath()
                + "\n" + externalFilesDir6.getAbsolutePath()
                + "\n" + externalFilesDir7.getAbsolutePath()
                + "\n" + externalFilesDir8.getAbsolutePath()
                + "\n" + externalFilesDir9.getAbsolutePath()
                + "\n" + externalFilesDir10.getAbsolutePath()
                + "\n" + externalFilesDir11.getAbsolutePath()
                + "\n" + externalFilesDir12.getAbsolutePath()
                + "\n" + externalFilesDir13.getAbsolutePath()
                + "\n" + externalFilesDir14.getAbsolutePath()
                + "\n" + externalFilesDir15.getAbsolutePath()
                + "\n" + externalFilesDir16.getAbsolutePath()
                + "\n" + externalFilesDir17.getAbsolutePath()
                + "\n" + externalFilesDir18.getAbsolutePath()
                + "\n" + externalFilesDir19.getAbsolutePath()
                + "\n" + externalFilesDir20.getAbsolutePath()
                + "\n" + externalFilesDir21.getAbsolutePath()
                + "\n" + externalFilesDir22.getAbsolutePath()
                + "\n" + externalFilesDir23.getAbsolutePath()
                + "\n" + externalFilesDir24.getAbsolutePath());
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.DemoHolder> {
        int[] a = new int[2];
        public final List<Integer> locationList = new ArrayList<>();

        @NonNull
        @Override
        public DemoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_item, parent, false);
            return new DemoHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DemoHolder holder, int position) {
            holder.itemView.getLocationOnScreen(a);
            locationList.add(a[0]);
            holder.tvTestData.setText(MessageFormat.format("测试数据{0}", position));
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        static class DemoHolder extends RecyclerView.ViewHolder {

            private final TextView tvTestData;

            public DemoHolder(@NonNull View itemView) {
                super(itemView);
                tvTestData = itemView.findViewById(R.id.tvTestData);
            }
        }
    }
}
