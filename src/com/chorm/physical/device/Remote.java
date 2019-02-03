package com.chorm.physical.device;

/**
 * The fundamental property of a remote.
 * chorm on 2019-02-03 11:46.
 * */
public interface Remote {

	void launchIR(int keyvalue, boolean isDown);
	
	void keyDown(int keyvalue);
	
	void keyUp(int keyvalue);
	
}
