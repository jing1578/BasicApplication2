package org.jing1578.basicapplication.applicattion;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.DisplayMetrics;
import android.widget.TextView;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import de.greenrobot.event.EventBus;

public class FragmentSupport extends Fragment {

    public int width;
    public int height;

    public List<TextView> listText;

    public MyApplication myApplication;
    public MyPreference myPreference;
    public MyCore myCore;

    private boolean inject = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwitchLanguage(SharedPre.getInstance(getActivity()).getLanguage());
//        EventBus.getDefault().register(this);
        initVar();
        listText = new ArrayList<>();

    }

    public void SwitchLanguage(String language) {
        Resources resource = getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        String en="en";
        String zh="zh";
        if (en.equals(language)) {
            configuration.locale = Locale.ENGLISH;
        } else if (zh.equals(language)) {
            configuration.locale = Locale.CHINESE;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        // 保存设置语言的类型
        SharedPre.getInstance(getActivity()).setLanguage(language);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    private void initVar() {
        myApplication = (MyApplication) getActivity().getApplication();
        myCore = myApplication.getMyCore();
        myPreference = myApplication.getMyPreference();
    }


    public void getWindowSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public void setTextSize(WebView web) {
        if (SharedPre.getInstance(getActivity()).getSize(SharedPre.FOURTEEN)) {
            if (web != null) {

                web.getSettings().setTextSize(WebSettings.TextSize.SMALLEST);
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(14);
            }
            return;
        }
        if (SharedPre.getInstance(getActivity()).getSize(SharedPre.SIXTEEN)) {
            if (web != null) {
                web.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(16);
            }
            return;
        }
        if (SharedPre.getInstance(getActivity()).getSize(SharedPre.EIGHTTEEN)) {
            if (web != null) {
                web.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(18);
            }
            return;
        }
        if (SharedPre.getInstance(getActivity()).getSize(SharedPre.TWENTY)) {
            if (web != null) {
                web.getSettings().setTextSize(WebSettings.TextSize.LARGER);
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(20);
            }
            return;
        }
    }


}
