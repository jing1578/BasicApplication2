package org.jing1578.basicapplication.dialog;



import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import org.jing1578.basicapplication.R;


/**
 *
 *
 * PopupWindow的构造函数为

 public PopupWindow(View contentView, int width, int height, boolean focusable)
 contentView为要显示的view，width和height为宽和高，值为像素值，也可以是MATCHT_PARENT和WRAP_CONTENT。

 focusable为是否可以获得焦点，这是一个很重要的参数，也可以通过

 public void setFocusable(boolean focusable)
 来设置，如果focusable为false，在一个Activity弹出一个PopupWindow，按返回键，由于PopupWindow没有焦点，会直接退出Activity。如果focusable为true，PopupWindow弹出后，所有的触屏和物理按键都有PopupWindows处理。

 如果PopupWindow中有Editor的话，focusable要为true。
 *   mPopupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
 *    mPopupWindow.showAsDropDown(v);
 */

public class PublishSelectPicPopupWindow extends PopupWindow {


	private Button btnHand;
	private Button btnLibrary;
	private Button btnTwocode;
	private Button btnCancel;
	private View mMenuView;

	@SuppressWarnings("deprecation")
	public PublishSelectPicPopupWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.publish_dialog, null);
		btnHand = (Button) mMenuView.findViewById(R.id.btn_hand);
		btnLibrary = (Button) mMenuView.findViewById(R.id.btn_library);
		btnTwocode=(Button) mMenuView.findViewById(R.id.btn_two_code);
		btnCancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
            public void onClick(View v) {
				dismiss();
			}
		});
		//设置按钮监听
		btnHand.setOnClickListener(itemsOnClick);
		btnLibrary.setOnClickListener(itemsOnClick);
		btnTwocode.setOnClickListener(itemsOnClick);
		btnCancel.setOnClickListener(itemsOnClick);
		//设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottom);
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		//mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {
			@Override
            public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});

	}

}
