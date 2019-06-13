package org.jing1578.baselibrary.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.jing1578.baselibrary.R;

import java.util.List;


/**
 * Created by jing1578 on 2016/11/25.
 */

public class EmptyTextWatcher implements TextWatcher {
    private List<EditText> editTextList;
    private TextView btnView;
    private Context context;

    public EmptyTextWatcher(Context context, List<EditText> editTextList, TextView btnView) {
        this.editTextList = editTextList;
        this.btnView=btnView;
        this.context=context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean isEmpty=false;
        for (EditText editText:editTextList){
           if (TextUtils.isEmpty(editText.getText().toString().trim())){
               isEmpty=true;
           }
        }
        if (!isEmpty){
            btnView.setEnabled(true);
            btnView.setBackgroundResource(R.drawable.btn_login_corner);
            btnView.setTextColor(Color.parseColor("#ffffff"));
        }else{
            btnView.setEnabled(false);
            btnView.setBackgroundResource(R.drawable.btn_login_corner_light);
            btnView.setTextColor(Color.parseColor("#a5e8a3"));
        }

    }


    public static  void initEmptyTextWatcher(Context context, List<EditText> editTextList, TextView btnView){
        boolean isEmpty=false;
        for (EditText editText:editTextList){
            if (TextUtils.isEmpty(editText.getText().toString().trim())){
                isEmpty=true;
            }
        }
        if (!isEmpty){
            btnView.setEnabled(true);
            btnView.setBackgroundResource(R.drawable.btn_login_corner);
            btnView.setTextColor(Color.parseColor("#ffffff"));
        }else{
            btnView.setEnabled(false);
            btnView.setBackgroundResource(R.drawable.btn_login_corner_light);
            btnView.setTextColor(Color.parseColor("#a5e8a3"));
        }
    }
}
