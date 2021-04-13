package cn.xiayiye5.xiayiye5library.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/4/13 10:59
 * 类描述 :
 */
public class ThreeFragment extends Fragment implements View.OnClickListener {

    private CurrentPage currentPage;
    private int position;

    public static Fragment getInstance(CurrentPage currentPage, int i) {
        ThreeFragment oneFragment = new ThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentPage", currentPage);
        bundle.putInt("position", i);
        oneFragment.setArguments(bundle);
        return oneFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentPage = (CurrentPage) getArguments().getSerializable("currentPage");
        Button btNext = getView().findViewById(R.id.bt_next);
        position = getArguments().getInt("position");
        btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        currentPage.setCurrentPage(position);
    }
}
