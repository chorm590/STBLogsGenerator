package com.chorm.random;



/**
 * 用于模拟机顶盒播放视频时产生的探针事件。
 * 
 * 一般来说，有PLAY_START事件就要有PLAY_QUIT事件。 只有极少数仅有其中一个事件的情况。
 * 
 * */
public class PlayerEventRandomGenerator implements Random<String> {
	
	private static final String TAG = "PlayerEventRandomGenerator";
	
/*

规范：事件类型|视频URL|节目名称|节目时长|节目画面质量|节目分类|是否首页推荐|首页推荐上线时间||||


*/

	@Override
	public String generate() {

		
		
		return null;
	}

}
