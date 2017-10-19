package org.jing1578.basicapplication.utils;

import android.app.Activity;
import android.os.Bundle;

import java.util.Set;

/**
 * Created by jing1578 on 2017/10/10 16:14.
 */

public class IntentUtil {

    public static void logExtra(Activity mContext){
       Bundle bundle=mContext.getIntent().getExtras();
        Set<String> keySet =bundle.keySet();
        for(String key : keySet) {
            LogUtil.e(mContext.getClass().getSimpleName()+"IntentUtil","intent传参数:"+key
                    +"结果:"+String.valueOf(bundle.get(key)));
        }
    }
}
