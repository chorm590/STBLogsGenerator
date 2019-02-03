package com.chorm.physical.player;

/**
 * 提供正常播放和SEEK播放功能。
 * */
public interface Player {

	void prepare(String url);
	
	void start(String url, int position);
	
	void pause();
	
	void resume(int position);
	
	void stop();
	
	void keyevent(int keyvalue, boolean isDown);
	
}
