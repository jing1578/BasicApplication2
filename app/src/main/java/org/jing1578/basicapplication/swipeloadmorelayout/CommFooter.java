package org.jing1578.basicapplication.swipeloadmorelayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jing1578.basicapplication.R;


/**
 * Created by LongYu on 2016/6/7.
 * recyclerview添加刷新尾部
 */
public class CommFooter {


    public static  CircleProgressBar progressBar;
    public static TextView textView;
    static LinearLayout mLlHasMore;

    protected CommFooter() {

    }

    public static View create(ViewGroup parent) {
        CommFooter footer = new CommFooter();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_loader, parent, false);
        progressBar=(CircleProgressBar)view.findViewById(R.id.pb_footer_load);
        textView=(TextView)view.findViewById(R.id.tv_footer_load);
        mLlHasMore=(LinearLayout)view.findViewById(R.id.ll_has_more);
        view.setTag(footer);
        return view;
    }

    public static void bind(View itemView, boolean hasMore) {
        CommFooter footer = (CommFooter) itemView.getTag();
        if (hasMore) {
            footer.textView.setVisibility(View.GONE);
//            footer.textView.setText("正在加载……");
            footer.progressBar.setVisibility(View.VISIBLE);
        } else {
            footer.textView.setVisibility(View.VISIBLE);
            footer.textView.setText("没有更多的数据了");
            footer.progressBar.setVisibility(View.GONE);
        }
    }
}
