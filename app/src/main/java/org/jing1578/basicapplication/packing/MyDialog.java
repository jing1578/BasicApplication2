package org.jing1578.basicapplication.packing;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jing1578 on 2017/9/22 10:08.
 */

public class MyDialog extends DialogFragment {

    private  DialogParams dialogParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        dialogParams=(DialogParams)bundle.getSerializable("dialogparams");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        if (dialogParams!=null) {
            if(!TextUtils.isEmpty(dialogParams.title)) {
                builder.setTitle(dialogParams.title);
            }
            if(!TextUtils.isEmpty(dialogParams.message)) {
                builder.setTitle(dialogParams.message);
            }
            if (dialogParams.layoutResId>0) {
                builder.setView(dialogParams.layoutResId);
            }
            if (dialogParams.view!=null){
                builder.setView(dialogParams.view);
            }
        }
        AlertDialog mAlertDialog=builder.create();
        return mAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
