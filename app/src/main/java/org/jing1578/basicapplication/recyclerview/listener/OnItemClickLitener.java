package org.jing1578.basicapplication.recyclerview.listener;

import android.view.View;

/**
 * Created by sgll on 2017/10/20.
 * 用于RecyclerView的点击事件
 */

public interface OnItemClickLitener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);
}
