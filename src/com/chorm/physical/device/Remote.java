package com.chorm.physical.device;

/**
 * The fundamental property of a remote.
 * chorm on 2019-02-03 11:46.
 * */
public interface Remote {

	void launchIR(int keyvalue, boolean isDown, Object obj);
	
	void keyDown(int keyvalue, Object obj);
	
	void keyUp(int keyvalue, Object obj);
	
	void key(int keyvalue, Object obj);
	
}
