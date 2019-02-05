package com.chorm.physical.device;

/**
 * 湖北M301H的遥控器。
 * */
public class M301HRemote extends RemoteBase {

	@Override
	public void keyDown(int keyvalue, Object obj) {
		launchIR(keyvalue, true, obj);
	}

	@Override
	public void keyUp(int keyvalue, Object obj) {
		launchIR(keyvalue, false, obj);
	}

}
