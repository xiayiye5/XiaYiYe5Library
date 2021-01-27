package cn.xiayiye5.xiayiye5library;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import cn.xiayiye5.xiayiye5library.utils.ToastUtil;

/**
 * @author : xiayiye5
 * @date : 2021/1/26 18:14
 * 类描述 :
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
        MyAdapter myAdapter = new MyAdapter();
        rvList.setAdapter(myAdapter);
        int[] a = new int[2];
        viewLine.getLocationOnScreen(a);
        //滚动到中间位置
        rvList.scrollToPosition(9);
        rvList.setItemViewCacheSize(myAdapter.getItemCount());
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
            holder.tvTestData.setText("测试数据" + position);
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
