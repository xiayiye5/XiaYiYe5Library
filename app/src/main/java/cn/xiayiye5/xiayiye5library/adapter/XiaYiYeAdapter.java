package cn.xiayiye5.xiayiye5library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * @author : xiayiye5
 * @date : 2021/2/8 14:12
 * 类描述 : 精简版适配器
 */
public abstract class XiaYiYeAdapter<E> extends RecyclerView.Adapter<XiaYiYeAdapter.XiaYiYeHolder> {

    private XiaYiYeHolder xiaYiYeHolder;
    List<E> data;
    private int layoutId;

    @NonNull
    @Override
    public XiaYiYeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        xiaYiYeHolder = new XiaYiYeHolder(view);
        return xiaYiYeHolder;
    }


    protected XiaYiYeAdapter(@LayoutRes int layoutId, List<E> data) {
        this.layoutId = layoutId;
        this.data = data;
    }


    @Override
    public void onBindViewHolder(@NonNull XiaYiYeHolder holder, int position) {
        coverData(holder, position);
    }

    protected abstract void coverData(XiaYiYeHolder holder, int position);


    public XiaYiYeHolder getXiaYiYeHolder() {
        return xiaYiYeHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class XiaYiYeHolder extends RecyclerView.ViewHolder {

        public XiaYiYeHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public TextView getTextView(@IdRes int id) {
        return xiaYiYeHolder.itemView.findViewById(id);
    }

    public XiaYiYeHolder setText(@IdRes int id, String data) {
        getTextView(id).setText(TextUtils.isEmpty(data) ? "数据为空" : data);
        return this.xiaYiYeHolder;
    }

    public XiaYiYeHolder setTextColor(@IdRes int id, @ColorInt int color) {
        getTextView(id).setTextColor(color);
        return this.xiaYiYeHolder;
    }

    public XiaYiYeHolder setTextColor(@IdRes int id, @Size(min = 1) String color) {
        getTextView(id).setTextColor(Color.parseColor(color));
        return this.xiaYiYeHolder;
    }
}
