package org.jing1578.basicapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jing1578 on 2017/10/20 10:31.
 * Android 重写HorizontalScrollView模仿ViewPager效果
 * 重写onTouchEvent事件，在手指抬起或者取消的时候，进行smoothScroll的操作
 * 左滑删除
 */

public class MyScrollView extends HorizontalScrollView {

    private Context context;
    private int subChildCount = 0;
    private ViewGroup firstChild;
    private int downX = 0;
    private int currentPage = 0;
    private ArrayList<Integer> pointList = new ArrayList<>();

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        firstChild = (ViewGroup) getChildAt(0);
        if (firstChild != null) {
            subChildCount = firstChild.getChildCount();
            for (int i = 0; i < subChildCount; i++) {
                if (firstChild.getChildAt(i).getWidth() > 0) {
                    pointList.add(firstChild.getChildAt(i).getLeft());
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (Math.abs(ev.getX() - downX) > getWidth() / 4) {
                    //右滑
                    if (ev.getX() - downX > 0) {
                        smoothScrollToPrePage();
                    } else {
                        //左滑
                        smoothScrollToNextPage();
                    }
                } else {
                    //当前页
                    smoothScrollToCurrent();
                }
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void smoothScrollToNextPage() {
        if (currentPage < subChildCount - 1) {
            currentPage++;
            smoothScrollTo(pointList.get(currentPage), 0);
        }
    }

    private void smoothScrollToPrePage() {
        if (currentPage > 0) {
            currentPage--;
            smoothScrollTo(pointList.get(currentPage), 0);
        }
    }

    private void smoothScrollToCurrent() {
        smoothScrollTo(pointList.get(currentPage), 0);
    }

    public void nextPage() {
        smoothScrollToNextPage();
    }

    public void prePage() {
        smoothScrollToPrePage();
    }

    public boolean gotoPage(int page) {
        if (page > 0 && page < subChildCount - 1) {
            smoothScrollTo(pointList.get(page), 0);
            currentPage = page;
            return true;
        }
        return false;
    }
}
