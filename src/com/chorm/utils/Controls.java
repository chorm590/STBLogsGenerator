package com.chorm.utils;

public class Controls {
	
	/**
	 * 牌照商提供的视频数量。
	 * */
	public static final int PROGRAMS_AMOUNT_LIMIT = 300;
	
	/**
	 * 家庭成员数量最少最多人数。
	 * */
	public static final int FAMILY_MEMBER_MIN = 1;
	public static final int FAMILY_MEMBER_MAX = 7;
	
	/**
	 * 湖北移动运营商向厂家定制的机顶盒数量。
	 * */
	public static final int HB_CMCC_STB_AMOUNT = 10000 * 10;
	
	/**
	 * 每个人喜欢看的节目类型。
	 * */
	public static final int PERSON_FAV_LABEL_MIN = 2;
	public static final int PERSON_FAV_LABEL_MAX = 5; //2019-02-05,upper limit is 8.
	
	/**
	 * 你要测试的家庭数量。
	 * */
	public static final int FAMILY_AMOUNT = 1; //2019-02-05 14:58
	
	/**
	 * 家庭成员实例化后执行看电视任务的相关延时值。
	 * */
	public static final int PERSON_WATCH_TV_PERIOD = 5000; // 5s
	public static final int PERSON_WATCH_TV_DELAY_MIN = 3000; // 30s
	public static final int PERSON_WATCH_TV_DELAY_MAX = 1200; // 120s
	
}
