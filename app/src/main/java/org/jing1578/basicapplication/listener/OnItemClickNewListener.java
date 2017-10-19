package org.jing1578.basicapplication.listener;

import android.view.View;

/**
 * Created by Administrator on 2017/6/23.
 */

public interface OnItemClickNewListener<T> {

    public void OnItemClick(View view, T t,int position);
}
