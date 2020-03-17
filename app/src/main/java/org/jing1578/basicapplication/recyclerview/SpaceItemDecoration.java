package org.jing1578.basicapplication.recyclerview;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by sgll on 2018/9/20.
 * RecyclerView 设置item间隔
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private boolean isVertical;

    public SpaceItemDecoration(int mSpace, boolean isVertical){
        this.mSpace = mSpace;
        this.isVertical = isVertical;
    }

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (isVertical) {
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;

            if(parent.getChildAdapterPosition(view) == 0){
                outRect.top = mSpace;
            }
        } else {
            if(parent.getChildAdapterPosition(view) == 0){
                outRect.left = mSpace;
            }
            outRect.right = mSpace;
            outRect.top = mSpace;
            outRect.bottom = mSpace;
        }


    }
}
