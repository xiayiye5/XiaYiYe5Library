package cn.xiayiye5.xiayiye5library.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author : xiayiye5
 * @date : 2021/3/26 15:37
 * 类描述 : 缩放view
 */
@SuppressLint("AppCompatCustomView")
public class ScaleView extends TextView implements View.OnTouchListener {
    private boolean isCanTouch = false;
    /**
     * 当前触摸的点数
     */
    private int point_num = 0;
    //最大的缩放比例
    public static final float SCALE_MAX = 8.0f;
    private static final float SCALE_MIN = 1.0f;

    private double oldDist = 0;
    private double moveDist = 0;
    /**
     * 针对控件的坐标系，即控件左上角为原点
     */
    private double moveX = 0;
    private double moveY = 0;

    private double downX = 0;
    private double downY = 0;
    // 针对屏幕的坐标系，即屏幕左上角为原点
    private double moveRawX = 0;
    private double moveRawY = 0;

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
        init();
    }

    private void init() {
        setIsCanTouch(true);
    }

    public void setIsCanTouch(boolean canTouch) {
        isCanTouch = canTouch;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!isCanTouch) {
            return false;
        }
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                point_num = 1;
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                point_num = 0;
                downX = 0;
                downY = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                if (point_num == 1) {
                    //只有一个手指的时候才有移动的操作
                    float lessX = (float) (downX - event.getX());
                    float lessY = (float) (downY - event.getY());
                    moveX = event.getX();
                    moveY = event.getY();
                    moveRawX = event.getRawX();
                    moveRawY = event.getRawY();
                    setSelfPivot(lessX, lessY);
                    //setPivot(getPivotX() + lessX, getPivotY() + lessY);
                } else if (point_num == 2) {
                    //只有2个手指的时候才有放大缩小的操作
                    moveDist = spacing(event);
                    double space = moveDist - oldDist;
                    float scale = (float) (getScaleX() + space / getWidth());
                    if (scale > SCALE_MIN && scale < SCALE_MAX) {
                        setScale(scale);
                    } else if (scale < SCALE_MIN) {
                        setScale(SCALE_MIN);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //两点按下时的距离
                oldDist = spacing(event);
                point_num += 1;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                point_num -= 1;
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 触摸使用的移动事件
     *
     * @param lessX x坐标
     * @param lessY y坐标
     */
    private void setSelfPivot(float lessX, float lessY) {
        float setPivotX = 0;
        float setPivotY = 0;
        setPivotX = getPivotX() + lessX;
        setPivotY = getPivotY() + lessY;
        Log.e("lawwingLog", "setPivotX:" + setPivotX + "  setPivotY:" + setPivotY
                + "  getWidth:" + getWidth() + "  getHeight:" + getHeight());
        if (setPivotX < 0 && setPivotY < 0) {
            setPivotX = 0;
            setPivotY = 0;
        } else if (setPivotX > 0 && setPivotY < 0) {
            setPivotY = 0;
            if (setPivotX > getWidth()) {
                setPivotX = getWidth();
            }
        } else if (setPivotX < 0 && setPivotY > 0) {
            setPivotX = 0;
            if (setPivotY > getHeight()) {
                setPivotY = getHeight();
            }
        } else {
            if (setPivotX > getWidth()) {
                setPivotX = getWidth();
            }
            if (setPivotY > getHeight()) {
                setPivotY = getHeight();
            }
        }
        setPivot(setPivotX, setPivotY);
    }

    /**
     * 计算两个点的距离
     *
     * @param event 事件
     * @return 返回数值
     */
    private double spacing(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return Math.sqrt(x * x + y * y);
        } else {
            return 0;
        }
    }

    /**
     * 平移画面，当画面的宽或高大于屏幕宽高时，调用此方法进行平移
     *
     * @param x 坐标x
     * @param y 坐标y
     */
    public void setPivot(float x, float y) {
        setPivotX(x);
        setPivotY(y);
    }

    /**
     * 设置放大缩小
     *
     * @param scale 缩放值
     */
    public void setScale(float scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

    /**
     * 初始化比例，也就是原始比例
     */
    public void setInitScale() {
        setScaleX(1.0f);
        setScaleY(1.0f);
        setPivot(getWidth() / 2f, getHeight() / 2f);
    }
}
