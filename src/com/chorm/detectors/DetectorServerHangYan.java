package com.chorm.detectors;

import com.chorm.output.GenericFileOutput;
import com.chorm.utils.Log;

public class DetectorServerHangYan {
	
	private static final String TAG = "DetectorServerHangYan";
	
	/**
	 * 探针日志内容头部固定部分的长度。
	 * 如：F300_26_372212345_
	 * */
	private int logsHeadLen = -1;
	
	private static DetectorServerHangYan mInstance;
	
	private DetectorInfo di;
	private StringBuilder sb;
	
	private DetectorServerHangYan() {
		di = new DetectorInfo();
		sb = new StringBuilder();
	}

	public static DetectorServerHangYan getInstance() {
		if(mInstance == null) {
			synchronized (DetectorServerHangYan.class) {
				if(mInstance == null) {
					mInstance = new DetectorServerHangYan();
				}
			}
		}
		
		return mInstance;
	}
	
	public DetectorInfo getDetectorInfo() {
		return di;
	}

	public void report(String serial, String mac, String info) {
		// step 1. Decoration
		
		/*
规则：地区编码_牌照方编码_厂家预留码_机顶盒序列号_机顶盒MAC#时区时间码#探针内容\r\n
如：F300_26_372212345_12345678123456781234567812345678_000C29867FBD#+81545264000#***
		 */
		if(di.manufactureCode == null || di.manufactureCode.equals(""))
			throw new IllegalStateException("There's not implements 'DetectorInfo' while in factory.");
		
		if(logsHeadLen == -1) {
			sb.delete(0, sb.length());
			sb.append(di.getAreaCode());
			sb.append("_");
			sb.append(di.getApkCode());
			sb.append("_");
			sb.append(di.getManufactureCode());
			sb.append("_");
			logsHeadLen = sb.length();
		}
		
		sb.delete(logsHeadLen, sb.length());
		sb.append(serial);
		sb.append('_');
		sb.append(mac);
		sb.append('_');
		sb.append("+8");
		sb.append(System.currentTimeMillis());
		sb.append('_');
		sb.append(info);
		
		// Step 2. print it/save it to file.
//		Log.info(TAG, sb.toString());
		GenericFileOutput.getInstance().write(sb.toString());
	}
	
	/**
	 * 部分探针上报需要用到的信息，厂家在生产的时候必须将信息填入。
	 * */
	public class DetectorInfo {
		
		private String areaCode;
		private String apkCode;
		private String manufactureCode;
		
		public String getAreaCode() {
			return areaCode;
		}
		
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
		
		public String getApkCode() {
			return apkCode;
		}
		
		public void setApkCode(String apkCode) {
			this.apkCode = apkCode;
		}
		
		public String getManufactureCode() {
			return manufactureCode;
		}
		
		public void setManufactureCode(String manufactureCode) {
			this.manufactureCode = manufactureCode;
		}
		
	}

}
