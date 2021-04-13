package cn.xiayiye5.xiayiye5library.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/4/13 10:59
 * 类描述 :
 */
public class FourFragment extends Fragment implements View.OnClickListener {

    private CurrentPage currentPage;

    public static Fragment getInstance(CurrentPage currentPage) {
        FourFragment oneFragment = new FourFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentPage", currentPage);
        oneFragment.setArguments(bundle);
        return oneFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentPage = (CurrentPage) getArguments().getSerializable("currentPage");
        Button btNextYes = getView().findViewById(R.id.bt_next_yes);
        Button btNextNo = getView().findViewById(R.id.bt_next_no);
        btNextYes.setOnClickListener(this);
        btNextNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_next_yes) {
            currentPage.setCurrentPage(4);
        } else {
            Toast.makeText(requireActivity(), "点击了第四个", Toast.LENGTH_LONG).show();
        }
    }
}
