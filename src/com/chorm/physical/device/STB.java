package com.chorm.physical.device;

import com.chorm.physical.device.RemoteBase.RemoteListener;
import com.chorm.physical.player.M301HPlayer;
import com.chorm.physical.player.Player;

public abstract class STB implements RemoteListener {

	private static final String TAG = "STB";
	
	private final String serial;
	private final String mac;
	private final String hardwareVersion;
	private String softwareVersion;
	
	private Remote mRemote;
	private Player mPlayer;
	
	protected STB(String serial, String mac, String hardwareVersion, String softwareVersion, Remote remote) {
		this.serial = serial;
		this.mac = mac;
		this.hardwareVersion = hardwareVersion;
		this.softwareVersion = softwareVersion;
		mRemote = remote;
		
		((RemoteBase)mRemote).registerSTBListener(this);
		initializePlayer();
	}
	
	private void initializePlayer() {
		mPlayer = new M301HPlayer();
	}

	/**
	 * 插上电源。
	 * */
	protected void powerOn() {
		bootup();
	}
	
	/**
	 * 拨电源。
	 * */
	protected void powerOff() {
		
	}
	
	/**
	 * 遥控器软开机。
	 * */
	protected abstract void bootup();
	
	/**
	 * 遥控器软关机。
	 * */
	protected abstract void shutdown();
	
	public Remote getRemote() {
		return mRemote;
	}
	
	/**
	 * 接收来自遥控器的事件。
	 * */
	@Override
	public void remote(int keyvalue, boolean isDown) {
		if(isDown)
			return;
		//Only process key up event.
		
	}
}
