package com.chorm.physical.device;

public abstract class RemoteBase implements Remote {
	
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
	}
	
	public interface RemoteListener {
		void remote(int keyvalue, boolean isDown);
	}

}
