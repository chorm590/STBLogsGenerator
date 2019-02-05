package com.chorm.physical.player;

import com.chorm.others.ProgramBeans;

/**
 * 提供正常播放和SEEK播放功能。
 * */
public interface Player {

	void prepare(ProgramBeans pb);
	
	void start(ProgramBeans pb, int position);
	
	void quit(ProgramBeans pb);

	void seek(ProgramBeans pb);
	
	void pause(ProgramBeans pb);
	
	void resume(ProgramBeans pb);
	
	void keyevent(int keyvalue, boolean isDown, Object obj);
	
}
