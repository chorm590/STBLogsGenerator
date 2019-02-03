package com.chorm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTools {

	/**
	 * @return eg: 2019-02-03
	 * */
	public static String getDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
	
	public static long currentSeconds() {
		return System.currentTimeMillis() / 1000;
	}
}
