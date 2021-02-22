package cn.xiayiye5.xiayiye5library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * created by lmj
 * describe:统一样式的列表item ui控件
 * time: 2019/5/6
 * Copyright© 2019 wyze.
 */
public class WpkListItemLayout extends RelativeLayout {

    private int type;
    private boolean hasToggle;
    private boolean hasArrow;
    private boolean hasExplain;
    public static final int TITLE_ONLY = 0;
    public static final int INFO_RIGHT = 1;
    public static final int INFO_BOTTOM = 2;
    public static final int INFO_RIGHT_BOTTOM = 3;

    private TextView tv_title_left;
    private TextView tv_info_right;
    private TextView tv_info_bottom;
    private ImageView iv_arrow;
    private ImageView iv_explain;
    private WpkSwitchButton toggle;
    private ImageView ivRightImg;

    private ImageView ivLeftIcon1;
    private ImageView ivLeftIcon2;
    private int iconResId1;
    private int iconResId2;

    private int title_text_color;
    private int tv_info_right_color;
    private int tv_info_bottom_color;
    private boolean isChecked;

    private OnCheckedChangeListener mCheckedChangeListener;


    public WpkListItemLayout(Context context) {
        super(context);
        init(context, null);
    }

    public WpkListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WpkListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        View view = LayoutInflater.from(context).inflate(R.layout.wpk_common_list_item_layout, this, true);

        ivLeftIcon1 = view.findViewById(R.id.iv_icon1);
        ivLeftIcon2 = view.findViewById(R.id.iv_icon2);
        tv_title_left = view.findViewById(R.id.tv_title_left);
        tv_info_right = view.findViewById(R.id.tv_info_right);
        tv_info_bottom = view.findViewById(R.id.tv_info_bottom);
        iv_arrow = view.findViewById(R.id.iv_arrow);
        iv_explain = view.findViewById(R.id.iv_explain);
        toggle = view.findViewById(R.id.toggle);
        ivRightImg = view.findViewById(R.id.iv_right);

        title_text_color = context.getResources().getColor(R.color.wyze_main_text_color);
        tv_info_right_color = context.getResources().getColor(R.color.color_7E8D92);
        tv_info_bottom_color = context.getResources().getColor(R.color.color_7E8D92);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WpkListItemLayout);
            type = a.getInteger(R.styleable.WpkListItemLayout_layout_type, 0);

            iconResId1 = a.getResourceId(R.styleable.WpkListItemLayout_left_icon1, 0);
            iconResId2 = a.getResourceId(R.styleable.WpkListItemLayout_left_icon2, 0);

            hasToggle = a.getBoolean(R.styleable.WpkListItemLayout_has_toggle, false);
            hasArrow = a.getBoolean(R.styleable.WpkListItemLayout_has_arrow, false);
            hasExplain = a.getBoolean(R.styleable.WpkListItemLayout_has_explain, false);

            title_text_color = a.getColor(R.styleable.WpkListItemLayout_title_text_color, context.getResources().getColor(R.color.wyze_main_text_color));
            tv_info_right_color = a.getColor(R.styleable.WpkListItemLayout_info_text_right_color, context.getResources().getColor(R.color.color_7E8D92));
            tv_info_bottom_color = a.getColor(R.styleable.WpkListItemLayout_info_text_bottom_color, context.getResources().getColor(R.color.color_7E8D92));
            isChecked = a.getBoolean(R.styleable.WpkListItemLayout_is_checked, false);

            String title_text = a.getString(R.styleable.WpkListItemLayout_title_text);
            String info_text_right = a.getString(R.styleable.WpkListItemLayout_info_text_right);
            String info_text_bottom = a.getString(R.styleable.WpkListItemLayout_info_text_bottom);
            a.recycle();

            tv_title_left.setText(title_text);
            tv_info_right.setText(info_text_right);
            tv_info_bottom.setText(info_text_bottom);

            if (iconResId1 != 0) {
                ivLeftIcon1.setVisibility(View.VISIBLE);
                ivLeftIcon1.setImageResource(iconResId1);
            }

            if (iconResId2 != 0) {
                ivLeftIcon2.setVisibility(View.VISIBLE);
                ivLeftIcon2.setImageResource(iconResId2);
            }
        }


        initStyle();

        if (hasArrow) {
            iv_arrow.setVisibility(View.VISIBLE);
        } else {
            iv_arrow.setVisibility(View.GONE);
        }

        if (hasToggle) {
            toggle.setVisibility(View.VISIBLE);
        } else {
            toggle.setVisibility(View.GONE);
        }

        if (hasExplain) {
            iv_explain.setVisibility(View.VISIBLE);
        } else {
            iv_explain.setVisibility(View.GONE);
        }

        ivRightImg.setVisibility(View.GONE);


        tv_title_left.setTextColor(title_text_color);
        tv_info_right.setTextColor(tv_info_right_color);
        tv_info_bottom.setTextColor(tv_info_bottom_color);
        toggle.setCheckedNoEvent(isChecked);
//        view.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckedChangeListener != null) {
                    mCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });


    }

    private void initStyle() {
        //下面type没有初始化值所以报错在预览页面不显示，所有添加下面的代码可以忽略预览布局页面不显示的问题
        if (isInEditMode()) {
            return;
        }
        switch (type) {
            case TITLE_ONLY:
                tv_info_right.setVisibility(View.INVISIBLE);
                tv_info_bottom.setVisibility(View.GONE);
                break;
            case INFO_RIGHT:
                tv_info_right.setVisibility(View.VISIBLE);
                tv_info_bottom.setVisibility(View.GONE);
                break;
            case INFO_BOTTOM:
                tv_info_right.setVisibility(View.INVISIBLE);
                tv_info_bottom.setVisibility(View.VISIBLE);
                break;
            case INFO_RIGHT_BOTTOM:
                tv_info_right.setVisibility(View.VISIBLE);
                tv_info_right.setTextColor(getContext().getResources().getColor(R.color.wyze_text_color_1C9E90));
                tv_info_bottom.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setStyle(int type) {
        this.type = type;
        initStyle();
    }

    public void setTitleText(String text) {
        tv_title_left.setText(text);
        tv_title_left.setVisibility(View.VISIBLE);
    }

    public void setTitleTextColor(int colorResId) {
        title_text_color = colorResId;
        tv_title_left.setTextColor(title_text_color);
    }

    public void setInfoTextRight(String text) {
        tv_info_right.setText(text);
        tv_info_right.setVisibility(View.VISIBLE);
    }

    public void setInfoTextRightColor(int colorResId) {
        tv_info_right_color = colorResId;
        tv_info_right.setTextColor(tv_info_right_color);
    }

    public void setInfoTextBottom(String text) {
        tv_info_bottom.setText(text);
        tv_info_bottom.setVisibility(View.VISIBLE);
    }

    public void setInfoTextBottomColor(int colorResId) {
        tv_info_bottom_color = colorResId;
        tv_info_bottom.setTextColor(tv_info_bottom_color);
    }

    public void setToggle(boolean isChecked) {
        toggle.setChecked(isChecked);
    }

    public void setToggleNoEvent(boolean isChecked) {
        toggle.setCheckedNoEvent(isChecked);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            tv_title_left.setTextColor(getContext().getResources().getColor(R.color.wyze_off_disabled));
            tv_info_right.setTextColor(getContext().getResources().getColor(R.color.wyze_off_disabled));
            tv_info_bottom.setTextColor(getContext().getResources().getColor(R.color.wyze_off_disabled));
        } else {
            tv_title_left.setTextColor(title_text_color);
            tv_info_right.setTextColor(tv_info_right_color);
            tv_info_bottom.setTextColor(tv_info_bottom_color);
        }
    }

    public void setHasArrow(boolean hasArrow) {
        if (hasArrow) {
            iv_arrow.setVisibility(View.VISIBLE);
        } else {
            iv_arrow.setVisibility(View.GONE);
        }
    }

    public void setHasToggle(boolean hasToggle) {
        if (hasToggle) {
            toggle.setVisibility(View.VISIBLE);
        } else {
            toggle.setVisibility(View.GONE);
        }
    }

    public void setImage(int resId) {
        if (resId != 0) {
            ivRightImg.setVisibility(View.VISIBLE);
            ivRightImg.setImageResource(resId);
        } else {
            ivRightImg.setVisibility(View.GONE);
        }
    }


    public void setLeftIcon1(int resId) {
        if (resId != 0) {
            ivLeftIcon1.setVisibility(View.VISIBLE);
            ivLeftIcon1.setImageResource(resId);
        } else {
            ivLeftIcon1.setVisibility(View.GONE);
        }
    }

    public void setLeftIcon2(int resId) {
        if (resId != 0) {
            ivLeftIcon2.setVisibility(View.VISIBLE);
            ivLeftIcon2.setImageResource(resId);
        } else {
            ivLeftIcon2.setVisibility(View.GONE);
        }
    }

    public interface OnClickImageListener {
        void onClick(ImageView imageView);
    }

    public void setOnClickImageListener(OnClickImageListener listener) {
        ivRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(ivRightImg);
            }
        });
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener mCheckedChangeListener) {
        this.mCheckedChangeListener = mCheckedChangeListener;
    }
}
