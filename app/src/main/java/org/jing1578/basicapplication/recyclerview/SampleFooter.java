package org.jing1578.basicapplication.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * Created by sgll on 2018/8/14.
 * RecyclerView的FooterView，简单的展示一个TextView
 */

public class SampleFooter extends RelativeLayout {
    public SampleFooter(Context context, int layoutResID) {
        super(context);
        init(context, layoutResID);
    }

    public SampleFooter(Context context, AttributeSet attrs, int layoutResID) {
        super(context, attrs);
        init(context, layoutResID);
    }

    public SampleFooter(Context context, AttributeSet attrs, int defStyleAttr, int layoutResID) {
        super(context, attrs, defStyleAttr);
        init(context, layoutResID);
    }

    private void init(Context context, int layoutResID){
        inflate(context, layoutResID, this);
    }
}
