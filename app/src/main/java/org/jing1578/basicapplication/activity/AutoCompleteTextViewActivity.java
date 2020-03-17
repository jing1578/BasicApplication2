package org.jing1578.basicapplication.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.ActivitySupport;

/**
 * Created by Administrator on 2019/12/24 09:57.
 */
public class AutoCompleteTextViewActivity extends ActivitySupport {

    private AutoCompleteTextView autocompletetextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocompletetextview);
        initView();
    }

    private void initView() {
        autocompletetextview = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.city_name,
                android.R.layout.simple_dropdown_item_1line);
        autocompletetextview.setAdapter(adapter);
    }
}
