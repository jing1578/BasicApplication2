package org.jing1578.basicapplication.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.activity.AutoCompleteTextViewActivity;
import org.jing1578.basicapplication.activity.WebViewActivity;
import org.jing1578.basicapplication.applicattion.FragmentSupport;
import org.jing1578.basicapplication.applicattion.MsgType;
import org.jing1578.basicapplication.activity.PopupWindowActivity;
import org.jing1578.basicapplication.activity.ViewPagerActivity;
import org.jing1578.basicapplication.dialog.PublishSelectPicPopupWindow;



/**
 * 消息页面
 */
public class ConversationListFragment extends FragmentSupport {
    private Context context;
    private View conversationListView;


    public ConversationListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context=getActivity();
        conversationListView=inflater.inflate(R.layout.fragment_conversationlist,container,false);
        conversationListView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实例化SelectPicPopupWindow
                menuWindow = new PublishSelectPicPopupWindow((Activity) context,itemsOnClick);
                // 显示窗口
                menuWindow.showAtLocation(conversationListView,Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        conversationListView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        conversationListView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        conversationListView.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });
        conversationListView.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(), WebView2Activity.class);
//                startActivity(intent);
            }
        });
        conversationListView.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AutoCompleteTextViewActivity.class);
                startActivity(intent);
            }
        });
        return conversationListView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        switch (MsgType.values()[msg.what]) {
            default:
                break;
        }
    }

    private PublishSelectPicPopupWindow menuWindow;


    /**
     *   弹出窗口实现监听类
     */
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_hand:
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_two_code:
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_library:
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    };




}
