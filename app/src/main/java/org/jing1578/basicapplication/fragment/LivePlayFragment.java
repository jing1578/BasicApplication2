package org.jing1578.basicapplication.fragment;

import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jing1578.basicapplication.applicattion.FragmentSupport;
import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.MsgType;


/**
 * 直播页面
 */

public class LivePlayFragment extends FragmentSupport {


    public LivePlayFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_liveplay,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        switch (MsgType.values()[msg.what]) {
            default:
                break;
        }
    }



}
