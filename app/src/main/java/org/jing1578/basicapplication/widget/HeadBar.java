package org.jing1578.basicapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jing1578.basicapplication.R;

/**
 * Created by jing1578 on 2017/10/19 14:49.
 * 头部框
 */

public class HeadBar extends RelativeLayout {

    private Context context;
    private TextView tvBack;
    private TextView tvRight;
    private TextView tvTitle;
    private View view;

    public HeadBar(Context context) {
        this(context, null);
    }

    public HeadBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.view_headbar, null);
        tvBack = (TextView) view.findViewById(R.id.tvBack);
        tvRight = (TextView) view.findViewById(R.id.tvRight);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        addView(view);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadBar);
        String title = typedArray.getString(R.styleable.HeadBar_textTitle);
        tvTitle.setText(title);
        typedArray.recycle();

    }

    /**
     *
     * @param b1
     */
    public void setBackIsHide(boolean b1){
        if (b1){
            tvBack.setVisibility(INVISIBLE);
        }else{
            tvBack.setVisibility(VISIBLE);
        }
    }

    /**
     *
     * @param b1
     */
    public void setRightIsHide(boolean b1){
        if (b1){
            tvRight.setVisibility(INVISIBLE);
        }else {
            tvRight.setVisibility(VISIBLE);
        }
    }

    /**
     *
     * @param id
     */
    public void setRightBackgroud(int id){
        tvRight.setBackgroundResource(id);
    }

    /**
     *
     * @param color
     */
    public void setRightTextColor(int color){
        tvRight.setTextColor(color);
    }

    /**
     *
     * @param color
     */
    public void setBackTextColor(int color){
        tvBack.setTextColor(color);
    }

    /**
     *
     * @param color
     */
    public void setTitleTextColor(int color){
        tvTitle.setTextColor(color);
    }

    /**
     *
     * @param text
     */
    public void setTitle(String text){
        tvTitle.setText(text);
    }

    /**
     *
     * @param text
     */
    public void setBackText(String text){
        tvBack.setText(text);
    }

    /**
     *
     * @param text
     * @param imgId
     */
    public void setBackText(String text,int imgId){
        tvBack.setText(text);
        if (imgId == 0){
           tvBack.setCompoundDrawables(null,null,null,null);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Drawable drawable =context.getResources().getDrawable(imgId,null);
            }else{
                Drawable drawable =context.getResources().getDrawable(imgId);
                //必须设置大小,否者不显示
                drawable.setBounds(0,0,45,50);
                tvBack.setCompoundDrawables(drawable,null,null,null);
            }
        }
    }

    /**
     *
     * @param text
     */
    public void setRightText(String text){
        tvRight.setText(text);
    }

    /**
     *
     * @param imgId
     */
    public void setRightIcon(int imgId){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable =context.getResources().getDrawable(imgId,null);
        }else{
            Drawable drawable =context.getResources().getDrawable(imgId);
            //必须设置大小,否者不显示
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            tvBack.setCompoundDrawables(drawable,null,null,null);
        }
    }


}
