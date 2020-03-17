package org.jing1578.basicapplication.recyclerview.listener;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


/**
 * Created by rs on 2017/10/20.
 * 继承自RecyclerView.OnScrollListener，可以监听到是否滑动到页面最低部
 */

public class OnRcvScrollListener extends RecyclerView.OnScrollListener implements OnListLoadNextPageListener {
    private String TAG = getClass().getSimpleName();

    public static enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    /**
     * layoutManager的类型（枚举）
     */
    protected LAYOUT_MANAGER_TYPE layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        // int lastVisibleItemPosition = -1;
        if(layoutManagerType == null){
            if(layoutManager instanceof LinearLayoutManager){
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            }else if(layoutManager instanceof GridLayoutManager){
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            }else if(layoutManager instanceof StaggeredGridLayoutManager){
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            }else{
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType){
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager)layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager)layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager)layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager= recyclerView.getLayoutManager();
        if(layoutManager instanceof StaggeredGridLayoutManager){
            ((StaggeredGridLayoutManager) layoutManager).invalidateSpanAssignments();
        }
        //得到显示屏幕内的list数量
        int visibleItemCount = layoutManager.getChildCount();
        //得到list的总数量
        int totalItemCount = layoutManager.getItemCount();
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                (lastVisibleItemPosition) >= totalItemCount - 1)) {
            //Log.d(TAG, "is loading more");
            onLoadNextPage();
        }
    }

    @Override
    public void onLoadNextPage() {

    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
