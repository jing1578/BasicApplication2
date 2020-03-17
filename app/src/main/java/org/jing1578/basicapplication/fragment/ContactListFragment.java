package org.jing1578.basicapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.FragmentSupport;

import org.jing1578.basicapplication.applicattion.MsgType;
import org.jing1578.basicapplication.widget.SearchBar;


/**
 * 联系人列表页(通讯录)
 */
public class ContactListFragment extends FragmentSupport {
    private Context context;
    private static final String TAG = ContactListFragment.class.getSimpleName();
    private View  contactListFragmentView;
    private static volatile ContactListFragment mContactListFragment;
    private SearchBar  searchBar;

    public static ContactListFragment getInstance(){//双重检验锁懒汉式单例模式
        if (mContactListFragment==null){
            synchronized (ContactListFragment.class){
                if (mContactListFragment==null){
                    mContactListFragment=new ContactListFragment();
                }
            }
        }
        return mContactListFragment;
    }

    public ContactListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        contactListFragmentView=inflater.inflate(R.layout.fragment_contactlist,container,false);
        context=getActivity();
        return contactListFragmentView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchBar = (SearchBar) contactListFragmentView.findViewById(R.id.searchBar);
        searchBar.setVoiceClickListener(new SearchBar.VoiceClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击语音按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        switch (MsgType.values()[msg.what]) {
            default:
                break;
        }
    }

}
