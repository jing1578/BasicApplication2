package org.jing1578.basicapplication.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jing1578.basicapplication.R;

import java.util.List;

/**
 * Created by zhouwei on 16/11/30.
 */

public class MyAdapter extends BaseQuickAdapter<String,MyAdapter.ViewHolder>{

    public void setData(List<String> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       super.onBindViewHolder(holder,position);
       holder.mTextView.setText(mData.get(position));
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_content);
        }
    }
}
