package com.chorm.physical.device;

import com.chorm.utils.Log;

public abstract class RemoteBase implements Remote {
	
	private static final String TAG = "RemoteBase";
	
	private RemoteListener remoteListener;
	
	/**
	 * 每套机顶盒都要适配一种遥控器。
	 * */
	public void registerSTBListener(RemoteListener listener) {
		remoteListener = listener;
	}

	protected void longPress(int keyvalue) {
		// TODO 
	}

	/**
	 * 将事件发送到机顶盒去。
	 * */
	@Override
	public void launchIR(int keyvalue, boolean isDown) {
		if(remoteListener != null)
			remoteListener.remote(keyvalue, isDown);
		else
			Log.info(TAG, "remote listener null");
	}
	
	public interface RemoteListener {
		void remote(int keyvalue, boolean isDown);
	}

}
