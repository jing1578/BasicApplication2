package org.jing1578.basicapplication.recyclerview;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Created by sgll on 2017/12/28.
 * RecyclerView为GridLayoutManager时，设置了HeaderView，就会用到这个SpanSizeLookup
 */

public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{
    private HeaderAndFooterRecyclerViewAdapter adapter;
    private int spanSize = 1;

    public HeaderSpanSizeLookup(HeaderAndFooterRecyclerViewAdapter adapter, int spanSize){
        this.adapter = adapter;
        this.spanSize = spanSize;
    }

    @Override
    public int getSpanSize(int position) {
        boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
        return isHeaderOrFooter ? spanSize : 1;
    }
}
