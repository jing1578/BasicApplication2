package com.tcmain.zxinglibrary.view;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public final class TCViewfinderResultPointCallback implements ResultPointCallback {

	private final TCViewfinderView viewfinderView;

	public TCViewfinderResultPointCallback(TCViewfinderView viewfinderView) {
		this.viewfinderView = viewfinderView;
	}

	@Override
	public void foundPossibleResultPoint(ResultPoint point) {
		viewfinderView.addPossibleResultPoint(point);
	}

}
