package com.chorm.detectors;

import com.chorm.others.DetectorType;
import com.chorm.utils.Log;

public abstract class DetectorBase implements Detector {
	
	private static final String TAG = "DetectorBase";
	
	protected final DetectorType mDetectorType;
	
	protected final StringBuilder sbDetectorEvent;
	
	/**
	 * 探针服务器地址。用于上报探针数据。
	 * */
	protected String serverUrl;
	
	protected DetectorBase(DetectorType dt) {
		mDetectorType = dt;
		sbDetectorEvent = new StringBuilder();
	}

	@Override
	public void report() {
		byte buf[] = sbDetectorEvent.toString().getBytes();
		// 将探针内容上报给探针服务器。
		// 这里仅将它输出到文件即可。
		Log.info(TAG, new String(buf, 0, buf.length) + " , length:" + buf.length);
		
		sbDetectorEvent.delete(0, sbDetectorEvent.length());
	}
	
	/**
	 * 往要上报的探针数据中添加内容。
	 * */
	protected void append(String value) {
		if(value == null || value.equals(""))
			throw new IllegalArgumentException("You must append a valid value.");
		sbDetectorEvent.append(value);
		sbDetectorEvent.append(SEPARATOR); //HeiHei.
	}

}
