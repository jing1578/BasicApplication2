package org.jing1578.basicapplication.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jing1578.basicapplication.R;


/**
 * Created by c-jiangshan on 2016/6/28.
 *
 */
public class LoadingDialogCreater {

    /**
     * DialogCreater dialogcreate=new DialogCreater(this);
     //        if (loadingDialog==null)
     l  oadingDialog= dialogcreate.createLoadingDialog("数据加载中");
     loadingDialog.show();
     */
    private static LoadingDialogCreater loadingDialog = null;

    private static Context context = null;

//    private static Dialog dialog = null;

    private OnInputDialogDismissListener onInputDialogDismissListener = null;
    private OnConfirmToDeleteData onConfirmToDeleteData = null;
    private OnRemindChoicedListener onRemindChoicedListener = null;
    private OnValueChangeListener onValueChangeListener = null;

    public OnValueChangeListener getOnValueChangeListener() {
        return onValueChangeListener;
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        this.onValueChangeListener = onValueChangeListener;
    }

    public OnRemindChoicedListener getOnRemindChoicedListener() {
        return onRemindChoicedListener;
    }

    public void setOnRemindChoicedListener(OnRemindChoicedListener onRemindChoicedListener) {
        this.onRemindChoicedListener = onRemindChoicedListener;
    }

    public OnConfirmToDeleteData getOnConfirmToDeleteData() {
        return onConfirmToDeleteData;
    }

    public void setOnConfirmToDeleteData(OnConfirmToDeleteData onConfirmToDeleteData) {
        this.onConfirmToDeleteData = onConfirmToDeleteData;
    }

    public synchronized static LoadingDialogCreater getInstance(Context context) {
//        if(loadingDialog == null){
        loadingDialog = new LoadingDialogCreater(context);
//        }
        return loadingDialog;
    }


    public LoadingDialogCreater(Context context) {
        LoadingDialogCreater.context = context;
    }

    public OnInputDialogDismissListener getOnInputDialogDismissListener() {
        return onInputDialogDismissListener;
    }

    public void setOnInputDialogDismissListener(OnInputDialogDismissListener onInputDialogDismissListener) {
        this.onInputDialogDismissListener = onInputDialogDismissListener;
    }

    /**
     * 没有文字的加载对话框
     *
     * @return
     */
    public Dialog createLoadingDialog() {
        return createLoadingDialog(null);
    }


    /**
     * 有文字的加载对话框
     *
     * @param msg
     * @return
     */
    public Dialog createLoadingDialog(String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字

        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);

        if (msg == null || msg.length() == 0) {
            tipTextView.setVisibility(View.GONE);
        } else {
            tipTextView.setText(msg);// 设置加载信息
            tipTextView.setVisibility(View.VISIBLE);
        }


        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;

    }

//
//    /**
//     * 数据输入的对话框
//     *
//     * @param view
//     * @param formatFlag
//     * @param title
//     */
//    public Dialog createDataInputDialog(final View view, final boolean formatFlag, final String title,final Map dataMap,String remark) {
//
//        LayoutInflater inflater = LayoutInflater.from(this.context);
//        View v = inflater.inflate(R.layout.dialog_data_input, null);// 得到加载view
//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//
//        TextView titleView = (TextView) v.findViewById(R.id.data_title);
//        titleView.setText(title);
//        Button cancelButton = (Button) v.findViewById(R.id.cancel_btn);
//        Button confirmButton = (Button) v.findViewById(R.id.confirm_btn);
//
//        final EditText inputData = (EditText) v.findViewById(R.id.input_value);
//        final EditText remarkData = (EditText) v.findViewById(R.id.remark_value);
//        if (formatFlag) {
//            inputData.setVisibility(View.GONE);
//            remarkData.setVisibility(View.VISIBLE);
//            remarkData.setHint("请输入" + title + "内容");
//            remarkData.setText(remark);
//
//        } else {
//            inputData.setVisibility(View.VISIBLE);
//            remarkData.setVisibility(View.GONE);
//            inputData.setHint("请输入" + title + "内容");
//            if(dataMap.get(title) != null){
//                inputData.setText(dataMap.get(title).toString());
//            }
//
//        }

//        final Dialog inputDialog = new Dialog(this.context, R.style.input_dialog);// 创建自定义样式dialog

//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputDialog.dismiss();
//            }
//        });
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String result = null;
//                if (formatFlag) {
//                    result = remarkData.getText().toString();
//                } else {
//                    result = inputData.getText().toString();
//                }
//                ((TextView) view).setText(result);
//                if (onValueChangeListener != null) {
//                    onValueChangeListener.onValueChange(view, title, result);
//                }
//                inputDialog.dismiss();
//            }
//        });
//
////        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
//        inputDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
////        inputDialog.show();
//
//        return inputDialog;
//    }

//    public Dialog createRemindDialog(String title) {
//        int padding10 = ScreenResolutionManager.getInstance(this.context).getPx(10);
//        LayoutInflater inflater = LayoutInflater.from(this.context);
//        View v = inflater.inflate(R.layout.dialog_data_input, null);// 得到加载view
//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//
//        TextView titleView = (TextView) v.findViewById(R.id.data_title);
//        titleView.setText(title);
//        Button cancelButton = (Button) v.findViewById(R.id.cancel_btn);
//        Button confirmButton = (Button) v.findViewById(R.id.confirm_btn);
//        confirmButton.setPadding(padding10,padding10,padding10,padding10);
//        cancelButton.setPadding(padding10,padding10,padding10,padding10);
//
//        EditText inputData = (EditText) v.findViewById(R.id.input_value);
//        EditText remarkData = (EditText) v.findViewById(R.id.remark_value);
//        inputData.setVisibility(View.GONE);
//        remarkData.setVisibility(View.GONE);
//
//        final Dialog inputDialog = new Dialog(this.context, R.style.input_dialog);// 创建自定义样式dialog
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputDialog.dismiss();
//            }
//        });
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onConfirmToDeleteData != null) {
//                    onConfirmToDeleteData.dileteData();
//                }
//            }
//        });
//
//        inputDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
//        Log.e("---", "===");
//        return inputDialog;
//
//    }


//    public Dialog createRemindDialog(String title,String btn1,String btn2) throws Exception {
//        int padding10 = ScreenResolutionManager.getInstance(this.context).getPx(10);
//        LayoutInflater inflater = LayoutInflater.from(this.context);
//        View v = inflater.inflate(R.layout.dialog_data_input, null);// 得到加载view
//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//
//        TextView titleView = (TextView) v.findViewById(R.id.data_title);
//        titleView.setText(title);
//        Button cancelButton = (Button) v.findViewById(R.id.cancel_btn);
//        Button confirmButton = (Button) v.findViewById(R.id.confirm_btn);
//        confirmButton.setPadding(padding10,padding10,padding10,padding10);
//        cancelButton.setPadding(padding10,padding10,padding10,padding10);
//        confirmButton.setText(btn2);
//        cancelButton.setText(btn1);
//
//        EditText inputData = (EditText) v.findViewById(R.id.input_value);
//        EditText remarkData = (EditText) v.findViewById(R.id.remark_value);
//        inputData.setVisibility(View.GONE);
//        remarkData.setVisibility(View.GONE);
//
//        final Dialog inputDialog = new Dialog(this.context, R.style.input_dialog);// 创建自定义样式dialog
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRemindChoicedListener.onChoicedResult(0);
//            }
//        });
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onRemindChoicedListener != null) {
//                    onRemindChoicedListener.onChoicedResult(1);
//                }
//            }
//        });
//
//        inputDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
//        Log.e("---", "===");
//        return inputDialog;
//
//    }

//    public Dialog createVoiceRemindDialog(JSONArray jsonArray) throws Exception{
//
//        int padding10 = ScreenResolutionManager.getInstance(this.context).getPx(10);
//        LayoutInflater inflater = LayoutInflater.from(this.context);
//        View v = inflater.inflate(R.layout.dialog_remind, null);// 得到加载view
//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//
//        TextView titleView = (TextView) v.findViewById(R.id.data_title);
//        titleView.setText("语音输入方式提示");
//
//
//
//        LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.voice_data_remind_layout);
//        TextView textView = new TextView(this.context);
//        textView.setTextColor(context.getResources().getColor(R.color.qiangdiao_text));
//        textView.setTextSize(18f);
//        textView.setText("日期：XXXX年XX月XX日");
//        linearLayout.addView(textView);
//        textView = new TextView(this.context);
//        textView.setTextColor(context.getResources().getColor(R.color.qiangdiao_text));
//        textView.setTextSize(18f);
//        textView.setText("时间：XX时XX分");
//        linearLayout.addView(textView);
//        if(jsonArray != null && jsonArray.length() > 0){
//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject object = (JSONObject)jsonArray.get(i);
//                String name = object.getString("typeName");
//                textView = new TextView(this.context);
//                textView.setTextColor(context.getResources().getColor(R.color.qiangdiao_text));
//                textView.setTextSize(18f);
//                textView.setText(name+"：XXX");
//                linearLayout.addView(textView);
//            }
//        }
//
//        textView = new TextView(this.context);
//        textView.setTextColor(context.getResources().getColor(R.color.qiangdiao_text));
//        textView.setTextSize(18f);
//        textView.setText("备注：XXXXXXXX");
//        linearLayout.addView(textView);
//
//
//
//        Button confirmButton = (Button) v.findViewById(R.id.confirm_btn);
//        confirmButton.setPadding(padding10, padding10, padding10, padding10);
//        confirmButton.setTextColor(context.getResources().getColor(R.color.white_color));
//
//        final Dialog inputDialog = new Dialog(this.context, R.style.input_dialog);// 创建自定义样式dialog
//
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputDialog.dismiss();
//            }
//        });
//
//        inputDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
//        Log.e("---", "===");
//        return inputDialog;
//
//    }



//    public static synchronized Dialog createPromptDialog(String title){
//
//        try{
//
//            int padding10 = ScreenResolutionManager.getInstance(context).getPx(10);
//            LayoutInflater inflater = LayoutInflater.from(context);
//            View v = inflater.inflate(R.layout.dialog_remind, null);// 得到加载view
//            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//
//            TextView titleView = (TextView) v.findViewById(R.id.data_title);
//            titleView.setText(title);
//
//
//
//            LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.voice_data_remind_layout);
//            linearLayout.setVisibility(View.GONE);
//            v.findViewById(R.id.label1).setVisibility(View.GONE);
//            v.findViewById(R.id.label2).setVisibility(View.GONE);
//
//
//            Button confirmButton = (Button) v.findViewById(R.id.confirm_btn);
//            confirmButton.setPadding(padding10, padding10, padding10, padding10);
//            confirmButton.setTextColor(context.getResources().getColor(R.color.white_color));
//
//            final Dialog dialog = new Dialog(context, R.style.input_dialog);// 创建自定义样式dialog
//
//            confirmButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.setContentView(layout, new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
//            return dialog;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }



    public interface OnInputDialogDismissListener {
        public void onInputDialogDismiss();
    }

    public interface OnConfirmToDeleteData {
        public void dileteData();
    }


    public interface OnRemindChoicedListener{
        public void onChoicedResult(int index);
    }

    public interface OnValueChangeListener{
        public void onValueChange(View view, String title, String newValue);
    }
}
