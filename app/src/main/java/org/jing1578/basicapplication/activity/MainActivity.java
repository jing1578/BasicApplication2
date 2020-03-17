package org.jing1578.basicapplication.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jing1578.baselibrary.utils.NetUtil;
import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.ActivitySupport;
import org.jing1578.basicapplication.applicattion.MsgType;
import org.jing1578.basicapplication.db.DBAction;
import org.jing1578.basicapplication.listener.NetStateListener;
import org.jing1578.basicapplication.receiver.NetBroadcastReceiver;
import org.jing1578.basicapplication.service.LongRunningService;
import org.jing1578.basicapplication.fragment.ContactListFragment;
import org.jing1578.basicapplication.fragment.ConversationListFragment;
import org.jing1578.basicapplication.fragment.FindFragment;
import org.jing1578.basicapplication.fragment.LivePlayFragment;
import org.jing1578.basicapplication.fragment.MineFragment;



/**
 * @author jing1578
 */
public class MainActivity extends ActivitySupport implements CompoundButton.OnCheckedChangeListener,NetStateListener {

    private ConversationListFragment fg_conversation;
    // private ContactsFragment fg_contacts;
    private ContactListFragment fg_contacts;
    private FindFragment fg_find;
    private LivePlayFragment fg_live;
    private MineFragment fg_mine;

    private Fragment currentFragment;
    private int checkPosition;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetBroadcastReceiver.mListeners.add(this);
        initContainer();
//        initSqlite();//初始化数据库,使用时可删除
//        registerNetworkReceiver();
        initService();
    }


    private RadioButton rb_message;
    private RadioButton rb_contacts;
    private RadioButton rb_find;
    private RadioButton rb_liveplay;
    private RadioButton rb_mine;

    private void initContainer() {
        rb_message = (RadioButton) findViewById(R.id.rb_maina_message);//
        rb_contacts = (RadioButton) findViewById(R.id.rb_maina_contacts);//
        rb_find = (RadioButton) findViewById(R.id.rb_maina_find);
        rb_liveplay = (RadioButton) findViewById(R.id.rb_maina_liveplay);
        rb_mine = (RadioButton) findViewById(R.id.rb_maina_mine);
        fg_conversation = new ConversationListFragment();
        fg_contacts = new ContactListFragment();
        fg_find = new FindFragment();
        fg_live = new LivePlayFragment();
        fg_mine = new MineFragment();
        rb_message.setOnCheckedChangeListener(this);
        rb_contacts.setOnCheckedChangeListener(this);
        rb_find.setOnCheckedChangeListener(this);
        rb_liveplay.setOnCheckedChangeListener(this);
        rb_mine.setOnCheckedChangeListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_maina_fragments, fg_conversation, "message").show(fg_conversation)
                .add(R.id.fl_maina_fragments, fg_contacts, "contacts").hide(fg_contacts)
                .add(R.id.fl_maina_fragments, fg_find, "find").hide(fg_find)
                .add(R.id.fl_maina_fragments, fg_live, "live").hide(fg_live)
                .add(R.id.fl_maina_fragments, fg_mine, "mine").hide(fg_mine)
                .commit();
        currentFragment = fg_conversation;
        checkPosition = 0;
        currentTabIndex = 0;
        rb_message.setChecked(true);
        rb_message.setTextColor(getResources().getColor(R.color.themcolor));
    }


    private void setCheckPostion(int postion) {
        if (checkPosition == postion) {
            return;
        }
        makeStateRBByPosition(checkPosition, false);
        checkPosition = postion;
        makeStateRBByPosition(checkPosition, true);//替换下方按钮的样式
        changeFragmentStateByPosition(checkPosition);//切换不同的fragment,hide,show;
    }

    private void makeStateRBByPosition(int position, boolean state) {
        switch (position) {
            case 0:
                rb_message.setChecked(state);
                rb_message.setTextColor(state ? getResources().getColor(R.color.themcolor) : getResources().getColor(R.color.grey));
                break;
            case 1:
                rb_contacts.setChecked(state);
                rb_contacts.setTextColor(state ? getResources().getColor(R.color.themcolor) : getResources().getColor(R.color.grey));
                break;
            case 2:
                rb_find.setChecked(state);
                rb_find.setTextColor(state ? getResources().getColor(R.color.themcolor) : getResources().getColor(R.color.grey));
                break;
            case 3:
                rb_liveplay.setChecked(state);
                rb_liveplay.setTextColor(state ? getResources().getColor(R.color.themcolor) : getResources().getColor(R.color.grey));
                break;
            case 4:
                rb_mine.setChecked(state);
                rb_mine.setTextColor(state ? getResources().getColor(R.color.themcolor) : getResources().getColor(R.color.grey));
                break;
            default:
                break;
        }
    }

    private void changeFragmentStateByPosition(int position) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.hide(currentFragment);
        switch (position) {
            case 0:
                currentFragment = fg_conversation;
                transaction.show(fg_conversation).commit();
                break;
            case 1:
                currentFragment = fg_contacts;
                transaction.show(fg_contacts).commit();
                break;
            case 2:
                currentFragment = fg_find;
                transaction.show(fg_find).commit();
                break;
            case 3:
                currentFragment = fg_live;
                transaction.show(fg_live).commit();
                break;
            case 4:
                currentFragment = fg_mine;
                transaction.show(fg_mine).commit();
                break;
            default:
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true) {
            switch (buttonView.getId()) {
                case R.id.rb_maina_message:
                    setCheckPostion(0);
                    currentTabIndex = 0;
                    break;
                case R.id.rb_maina_contacts:
                    setCheckPostion(1);
                    currentTabIndex = 1;
                    break;
                case R.id.rb_maina_find:
                    setCheckPostion(2);
                    currentTabIndex = 2;
                    break;
                case R.id.rb_maina_liveplay:
                    setCheckPostion(3);
                    currentTabIndex = 3;
                    break;
                case R.id.rb_maina_mine:
                    setCheckPostion(4);
                    currentTabIndex = 4;
                    break;
                default:
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        switch (MsgType.values()[msg.what]) {
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//       unregisterNetworkReceiver();
    }

    /////////////////////////////////////////测试代码/////////////////////////////////////////////////////////////////////////
//    private MyDatabaseHelper dbHelper;
    private  void  initSqlite(){
//        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
//        dbHelper.getWritableDatabase();
////        dbHelper.addValue(dbHelper);
        DBAction.getInstance(this);
    }

//    private  NetworkChangeReceiver networkChangeReceiver;
//    private IntentFilter intentFilter;
//    private void registerNetworkReceiver(){
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);
//    }

//    private void unregisterNetworkReceiver(){
//        unregisterReceiver(networkChangeReceiver);
//    }


//    class NetworkChangeReceiver extends BroadcastReceiver {//监听网络广播
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectionManager = (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isAvailable()) {
//                Toast.makeText(context, "network is available",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "network is unavailable",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void initService() {
        startService(new Intent(MainActivity.this,LongRunningService.class));
    }

    /**
     * 启动活动最佳示范
     * @param context
     * @param data1
     * @param data2
     */
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    @Override
    public void onNetChange() {
        NetUtil.onNetChange(this);
    }


//    PopupWindow mPopupWindow;
//    Button  mButton;
//    private void  showPopupWindow(){
//        View popupView = getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
//
//        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        mPopupWindow.setTouchable(true);
//        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
//
//        mButton = (Button) findViewById(R.id.btn_test_popupwindow);
//        mButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                mPopupWindow.showAsDropDown(v);
////                mPopupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
//            }
//        });
//    }


}
