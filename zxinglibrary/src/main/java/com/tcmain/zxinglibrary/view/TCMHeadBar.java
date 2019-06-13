package com.tcmain.zxinglibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tcmain.zxinglibrary.R;
import com.tcmain.zxinglibrary.util.WindowsManagerUtil;

/**
 * Created by sgll on 2018/10/25.
 */
public class TCMHeadBar extends RelativeLayout {
    private Context context;
    private WindowsManagerUtil windowsManagerUtil;
    private View view;
    private TextView tvBack;
    private TextView tvTitle;

    public TCMHeadBar(Context context) {
        super(context);
        addHeadView(context);
    }

    public TCMHeadBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        addHeadView(context);
    }

    public TCMHeadBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addHeadView(context);
    }

    /**
     * 加载视图
     *
     * @param context
     */
    private void addHeadView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_tctitle, null);
        windowsManagerUtil = new WindowsManagerUtil(context);
        initView();

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        addView(view);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        tvBack = view.findViewById(R.id.tv_back);
        tvTitle = view.findViewById(R.id.textview_title);
    }



    /**
     * 设置设置标题的颜色
     *
     * @param color
     */
    public void setTitleTextColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 返回按钮是否隐藏
     *
     * @param bl
     */
    public void setBackIsHide(boolean bl) {
        if (bl) {
            tvBack.setVisibility(INVISIBLE);
        } else {
            tvBack.setVisibility(VISIBLE);
        }
    }


    /**
     * 设置左边按钮的显示信息
     *
     * @param txt
     */
    public void setLeftText(String txt, int imgId) {
        if(txt.length() > 2){
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            params.setMargins(windowsManagerUtil.dip2px(10), 0, 0 , 0);
            tvBack.setLayoutParams(params);
        }

        tvBack.setText(txt);
        if (imgId == 0) {
            tvBack.setCompoundDrawables(null, null, null, null);
        } else {
            Drawable drawable = context.getResources().getDrawable(imgId);

            if(TextUtils.isEmpty(txt)){
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
            }else{
                drawable.setBounds(0, 0, 45, 50);//必须设置图片大小，否则不显示
            }

            tvBack.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置左边按钮的显示信息
     *
     * @param txt
     */
    public void setLeftText(String txt) {
        tvBack.setText(txt);
    }

    /**
     * 设置设置右侧按钮的颜色
     *
     * @param color
     */
    public void setLeftTextColor(int color) {
        tvBack.setTextColor(color);
    }

    /**
     * 设置标题栏背景颜色
     * @param color
     */
    public void setBackground(int color) {
        view.setBackgroundColor(color);
    }



    /**
     * 自定义点击事件
     *
     * @param ol
     */
    public void setHeadBarOnclick(OnClickListener ol) {
        tvBack.setOnClickListener(ol);
    }
}
