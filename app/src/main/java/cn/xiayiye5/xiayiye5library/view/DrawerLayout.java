package cn.xiayiye5.xiayiye5library.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author : xiayiye5
 * @date : 2021/3/15 10:55
 * 类描述 :
 */
public class DrawerLayout extends FrameLayout {
    private ViewDragHelper mDragHelper;
    private Status mStatus = Status.Close;
    private Status target = mStatus;
    private int menuContainer;

    /**
     * {@link DrawerLayout#replaceMenuView(FragmentManager, Fragment)}
     *
     * @param v
     * @return
     */
    @Deprecated
    public DrawerLayout replaceMenuView(View v) {
        v.measure(0, 0);
        mMenuViewHeight = v.getMeasuredHeight();
        mMenuView.removeAllViews();
        mMenuView.addView(v);
        return this;
    }

    /**
     * {@link DrawerLayout#replaceMenuView(FragmentManager, Fragment)}
     *
     * @param resId
     */
    @Deprecated
    public DrawerLayout replaceMenuView(int resId) {
        View inflate = LayoutInflater.from(getContext()).inflate(resId, mMenuView, false);
        replaceMenuView(inflate);
        return this;
    }

    public DrawerLayout replaceMenuView(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction().replace(menuContainer, fragment).commitAllowingStateLoss();
        return this;
    }

    public DrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
        setFocusable(true);
        setBackgroundColor(Color.BLACK);
        mDragHelper = ViewDragHelper.create(this, mCallback);
    }

    public DrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View arg0, int arg1) {
            return false;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            float percent = (getHeight() - top) * 1.0f / mMenuViewHeight;
            animViews(percent);
            mStatus = updateStatus(percent);
            if (mStatus == Status.Close) {
                mShadeView.setVisibility(INVISIBLE);
            } else {
                mShadeView.setVisibility(VISIBLE);
            }
            invalidate();
        }

        ;
    };

    private Status updateStatus(float percent) {
        if (percent == 0f) {
            return Status.Close;
        } else if (percent == 1.0f) {
            return Status.Open;
        }
        return Status.Draging;
    }

    private void animViews(float percent) {
        mMainView.setScaleX(evaluate(percent, 1.0f, 0.8f));
        mMainView.setScaleY(evaluate(percent, 1.0f, 0.8f));
        mMainView.setTranslationY(evaluate(percent, 0, -0.4f * mMenuViewHeight));
        int color = evaluateColor(percent, Color.TRANSPARENT, 0x55000000);
        mShadeView.setBackgroundColor(color);
    }

    public Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }

    public int evaluateColor(float fraction, int startValue, int endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    private ViewGroup mMenuView;
    private ViewGroup mMainView;
    private int mMenuViewHeight;
    private View mShadeView;
    private OnStateChangeListener listener;
    private Rect mTouchFrame;

    public void open(boolean isSmooth) {
        target = Status.Open;
        if (isSmooth) {
            int top = getHeight() - mMenuViewHeight;
            if (mDragHelper.smoothSlideViewTo(mMenuView, 0, top > 0 ? top : 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            if (listener != null) {
                listener.onStateChange(true);
            }
        } else {
        }
    }

    public void close(boolean b) {
        target = Status.Close;
        if (b) {
            if (mDragHelper.smoothSlideViewTo(mMenuView, 0, getHeight())) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            if (listener != null) {
                listener.onStateChange(false);
            }
        } else {

        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() < 2) {
            throw new IllegalStateException(" Your ViewGroup must have 2 ViewGroup children at least.");
        }
        if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException("Your children must be an instance of ViewGroup");
        }

        mMenuView = (ViewGroup) getChildAt(1);
        mMainView = (ViewGroup) getChildAt(0);
        mShadeView = new View(getContext());
        mShadeView.setBackgroundColor(Color.TRANSPARENT);
        mMainView.addView(mShadeView);
        menuContainer = mMenuView.getId();
        if (menuContainer == NO_ID) {
//            menuContainer = R.id.view_group_drawer_layout_container;
            mMenuView.setId(menuContainer);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuViewHeight = mMenuView.getMeasuredHeight();
        if (mStatus != Status.Close) {
            mDragHelper.abort();
            if (target == Status.Close) {
                close(true);
            } else {
                open(true);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mStatus == Status.Close) {
            mMenuView.layout(left, bottom, right, bottom + mMenuViewHeight);
        } else if (mStatus == Status.Open) {
            mMenuView.layout(left, bottom - mMenuViewHeight, right, bottom);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int a = ev.getAction();

        if (MotionEvent.ACTION_DOWN == a && mStatus != Status.Close) {
            if (!inRangeOfView((int) ev.getX(), (int) ev.getY())) {
                close(true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean inRangeOfView(int x, int y) {
        Rect frame = mTouchFrame;
        if (frame == null) {
            mTouchFrame = new Rect();
            frame = mTouchFrame;
        }
        if (mMenuView.getVisibility() == VISIBLE) {
            mMenuView.getHitRect(frame);
            return frame.contains(x, y);
        }
        return false;
    }

    public enum Status {
        Close, Open, Draging;
    }

    public boolean isOpen() {
        return mStatus == Status.Open;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && isOpen()) {
            // 事件拦截失败........
            close(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        this.listener = listener;
    }

    public static interface OnStateChangeListener {
        void onStateChange(boolean open);
    }
}
