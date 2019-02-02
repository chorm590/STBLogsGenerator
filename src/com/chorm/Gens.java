package com.chorm;

import java.util.Date;

import com.chorm.utils.JsonUtil;
import com.chorm.utils.Log;

import junit.MyTest;

public class Gens {
	
	private static final String TAG = "Gens";

	public static void main(String[] args) {
//		Date time = new Date();
//        Object json = JsonUtil.fastToJson(time);
//        //Object json = JSON.toJSONStringWithDateFormat(time, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
//        Log.info(TAG, json.toString());
		
		new MyTest().randomTest();
	}
	
	

}
