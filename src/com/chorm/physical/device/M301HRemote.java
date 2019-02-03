package com.chorm.physical.device;

/**
 * 湖北M301H的遥控器。
 * */
public class M301HRemote extends RemoteBase {

	@Override
	public void keyDown(int keyvalue) {
		launchIR(keyvalue, true);
	}

	@Override
	public void keyUp(int keyvalue) {
		launchIR(keyvalue, false);
	}

}
