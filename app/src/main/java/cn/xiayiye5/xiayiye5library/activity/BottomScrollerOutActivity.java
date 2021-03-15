package cn.xiayiye5.xiayiye5library.activity;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/3/15 09:59
 * 类描述 :
 */
public class BottomScrollerOutActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_scroller_out;
    }

    @Override
    protected void initId() {
        //底部抽屉栏展示地址
        RelativeLayout bottomSheet = findViewById(R.id.bottom_sheet);
        BottomSheetBehavior<RelativeLayout> behavior = BottomSheetBehavior.from(bottomSheet);
        //setBottomSheetCallback已过时
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                String state = "null";
                switch (newState) {
                    case 1:
                        state = "STATE_DRAGGING";//过渡状态此时用户正在向上或者向下拖动bottom sheet
                        break;
                    case 2:
                        state = "STATE_SETTLING"; // 视图从脱离手指自由滑动到最终停下的这一小段时间
                        break;
                    case 3:
                        state = "STATE_EXPANDED"; //处于完全展开的状态

                        break;
                    case 4:
                        state = "STATE_COLLAPSED"; //默认的折叠状态
                        break;
                    case 5:
                        state = "STATE_HIDDEN"; //下滑动完全隐藏 bottom sheet
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
            }
        });
    }


    @Override
    protected void loadData() {

    }
}
