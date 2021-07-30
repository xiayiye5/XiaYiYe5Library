package cn.xiayiye5.xiayiye5library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.activity.FragmentReplaceActivity;

/**
 * @author : xiayiye5
 * @date : 2021/7/30 10:43
 * 类描述 : fragment回退栈页面
 */
public class FragmentOne extends Fragment {
    private final FragmentReplaceActivity activity;
    private final Fragment fragment;
    private final String data;
    private final int contentId;
    private final int progress;

    public FragmentOne(FragmentReplaceActivity activity, String data, Fragment fragment, int contentId, int progress) {
        this.activity = activity;
        this.fragment = fragment;
        this.data = data;
        this.contentId = contentId;
        this.progress = progress;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(requireActivity(), R.layout.fragment_one, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity.setTopProgress(progress);
        Button btGoNext = getView().findViewById(R.id.bt_go_next);
        TextView tvFragment = getView().findViewById(R.id.tv_fragment);
        tvFragment.setText(data);
        btGoNext.setOnClickListener(this::goNext);
    }

    public void goNext(View view) {
        if (null == fragment) {
            Toast.makeText(getContext(), "已经是最后一个页面了", Toast.LENGTH_LONG).show();
            return;
        }
        getFragmentManager().beginTransaction()
                .replace(contentId, fragment, fragment.getClass().getSimpleName())
                //增加回退栈
                .addToBackStack(fragment.getClass().getSimpleName())
                //增加动画
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                //而采用commitAllowingStateLoss()方式提交事务，在执行时不会检查mStateSaved的值，不会发生异常。
                .commitAllowingStateLoss();
    }
}
