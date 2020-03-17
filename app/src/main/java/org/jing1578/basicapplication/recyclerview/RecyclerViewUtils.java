package org.jing1578.basicapplication.recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by rs on 2017/11/20.
 * RecyclerView设置Header/Footer所用到的工具类
 * RecyclerView添加了HeaderView, 再调用ViewHolder类的getAdapterPosition()、getLayoutPosition()时,
 * 返回的值就会因为增加了Header而受影响（返回的position等于真实的position+headerCounter）
 * 因此，这种情况下请使用 RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this)、RecyclerViewUtils.getLayoutPosition(mRecyclerView, ViewHolder.this) 两个方法来替代
 */

public class RecyclerViewUtils {
    /**
     * 设置HeaderView
     * @param recyclerView
     * @param view
     */
    public static void setHeaderView(RecyclerView recyclerView, View view){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if(outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)){
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        if(headerAndFooterAdapter.getHeaderViewsCount() == 0){
            headerAndFooterAdapter.addHeaderView(view);
        }
    }

    /**
     * 设置FooterView
     * @param recyclerView
     * @param view
     */
    public static void setFooterView(RecyclerView recyclerView, View view){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if(outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)){
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        if(headerAndFooterAdapter.getFooterViewsCount() == 0){
            headerAndFooterAdapter.addFooterView(view);
        }
    }

    /**
     * 移除FooterView
     * @param recyclerView
     */
    public static void removeFooterView(RecyclerView recyclerView){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if(outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter){
            int footerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount();
            if(footerViewCounter > 0){
                View footerView = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
                ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(footerView);
            }
        }
    }

    /**
     * 移除HeaderView
     * @param recyclerView
     */
    public static void removeHeaderView(RecyclerView recyclerView){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if(outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter){
            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if(headerViewCounter > 0){
                View headerView = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderView();
                ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(headerView);
            }
        }
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if(outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter){
            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if(headerViewCounter > 0){
                return holder.getLayoutPosition() - headerViewCounter;
            }
        }

        return holder.getLayoutPosition();
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getAdapterPosition()方法
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getAdapterPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder){
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if(outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter){
            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if(headerViewCounter > 0){
                return holder.getAdapterPosition() - headerViewCounter;
            }
        }

        return holder.getAdapterPosition();
    }
}
