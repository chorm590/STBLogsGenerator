package com.chorm.utils;

/**
 * Inverse the decimal numeric to hex numeric.
 * */
public class NumericTools {
	
	private static final StringBuilder sb = new StringBuilder();

	public static String int2hexString(int n) {
		return Integer.toHexString(n);
	}
	
	/**
	 * @return eg: 0 -> 00, 3 -> 03, 10 -> 10, 33115 -> 33115, -1 -> -01, -14 -> -14.
	 * */
	public static String int2String(int n) {
		sb.delete(0, sb.length());
		
		if(n < 0)
			sb.append("-");
		
		if(n >= 0 && n < 10)
			sb.append("0");
		
		sb.append(Integer.toString(n));
		
		return sb.toString();
	}
}
