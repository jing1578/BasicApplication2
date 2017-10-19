package org.jing1578.basicapplication.widget;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.jing1578.basicapplication.R;

/**
 * Created by jing1578 on 2017/10/19 16:21.
 */

public class SearchBar extends RelativeLayout {

    private Context context;
    private View view;
    private EditText query;
    private ImageButton search_clear;
    private ImageView img_void;

    private VoiceClearEditTextWatcher voiceClearEditTextWatcher;
    private VoiceClickListener voiceClickListener;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.ease_search_bar, null);
        initView(context,view);
        addView(view);
    }

    public void initView(Context context,View view){
        this.context=context;
        query = (EditText) view.findViewById(R.id.query);
        // 搜索框中清除button
        img_void = (ImageView) view.findViewById(R.id.img_void);
        search_clear = (ImageButton) view.findViewById(R.id.search_clear);
        query.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    search_clear.setVisibility(View.VISIBLE);
                } else {
                    search_clear.setVisibility(View.INVISIBLE);
                }
                if (voiceClearEditTextWatcher!=null) {
                    voiceClearEditTextWatcher.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (voiceClearEditTextWatcher!=null) {
                    voiceClearEditTextWatcher.beforeTextChanged(s, start, count,after);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString().trim())) {
                    img_void.setVisibility(View.GONE);
                } else {
                    img_void.setVisibility(View.VISIBLE);
                }
                if (voiceClearEditTextWatcher!=null) {
                    voiceClearEditTextWatcher.afterTextChanged(s);
                }
            }
        });
        search_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
                hideSoftKeyboard();
            }
        });
        img_void.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voiceClickListener!=null) {
                    voiceClickListener.onClick(v);
                }
            }
        });
    }


    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)((Activity)context).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (((Activity)context).getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (((Activity)context).getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void setVoiceClearEditTextWatcher(VoiceClearEditTextWatcher voiceClearEditTextWatcher) {
        this.voiceClearEditTextWatcher = voiceClearEditTextWatcher;
    }

    public void setVoiceClickListener(VoiceClickListener voiceClickListener) {
        this.voiceClickListener = voiceClickListener;
    }




    public interface VoiceClearEditTextWatcher{
        public void onTextChanged(CharSequence s, int start, int before, int count);

        public void beforeTextChanged(CharSequence s, int start, int count, int after);

        public void afterTextChanged(Editable s);
    }

    public interface VoiceClickListener{
        public void onClick(View v);
    }
}
