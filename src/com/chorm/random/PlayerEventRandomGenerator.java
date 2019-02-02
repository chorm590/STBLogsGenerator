package com.chorm.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chorm.utils.Log;

/**
 * 用于模拟机顶盒播放视频时产生的探针事件。
 * 
 * */
public class PlayerEventRandomGenerator extends RandomBase {
	
	private static final String TAG = "PlayerEventRandomGenerator";
	
	private Map<Integer, Integer> map = new HashMap<>();
	
	public static final int PLAY_PREPARE = 1;
	public static final int PLAY_START = 2;
	public static final int SEEK_START = 3;
	public static final int PAUSE_MESSAGE = 4;
	public static final int RESUME_MESSAGE = 5;
	public static final int BUFFER_START = 6;
	public static final int BUFFER_END = 7;
	public static final int PLAY_QUIT = 8;
	public static final int PLAY_REPORT = 9;
	public static final int PLAY_LIVE = 10;
	
	/**
	 * 取随机数的时候用这个。
	 * */
	public static final int PLAY_EVENT[] = {
			PLAY_PREPARE  ,
			PLAY_START    ,
			SEEK_START    ,
			PAUSE_MESSAGE ,
			RESUME_MESSAGE,
			BUFFER_START  ,
			BUFFER_END    ,
			PLAY_QUIT     ,
			PLAY_REPORT   ,
			PLAY_LIVE     ,
	};

	@Override
	public String generate() {
//		int ri = 0;
//		map.clear();
//		long ss = System.currentTimeMillis();
//		for(int i = 0; i < 100000000; i++) {
//			ri = (int)(jutilRandom.nextDouble() * 10);
//			if(map.containsKey(ri))
//				map.put(ri, map.get(ri) + 1);
//			else
//				map.put(ri, 1);
//		}
//		Log.info(TAG, (System.currentTimeMillis() - ss) + "ms");
//		System.out.println("---------------------------------------");
//		Set<Integer> s = map.keySet();
//		for(Integer i : s) {
//			Log.info(TAG, i + " > " + map.get(i));
//		}
//		System.out.println("---------------------------------------");
		
		
		return null;
	}

}
