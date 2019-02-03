package com.chorm.physical.device;

import java.util.HashSet;
import java.util.Set;

import com.chorm.detectors.Detector;
import com.chorm.others.Operator;
import com.chorm.others.ProgramBeans;
import com.chorm.others.Province;
import com.chorm.physical.device.RemoteBase.RemoteListener;
import com.chorm.physical.player.Player;
import com.chorm.utils.Log;

/**
 * 这里的机顶盒有些这功能就足够了呀。
 * chorm 2019-02-03 16:15.
 * */
public abstract class STB implements RemoteListener {

	private static final String TAG = "STB";
	
	protected String serial;
	protected String mac;
	protected String hardwareVersion;
	protected String softwareVersion;

	protected Operator operator;
	protected Province province;
	
	protected Remote mRemote;
	protected Player mPlayer;
	protected Detector mDetector;
	
	protected Set<ProgramBeans> programs;
	
	protected STB(String serial, String mac, String hwv, String swv,
			Operator o, Province p, Remote r, Player p2,
			Detector d) {
		this.serial = serial;
		this.mac = mac;
		this.hardwareVersion = hwv;
		this.softwareVersion = swv;
		this.operator = o;
		this.province = p;
		this.mRemote = r;
		this.mPlayer = p2;
		mDetector = d;
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
		// Do nothing.
	}
	
	/**
	 * 遥控器软开机。
	 * */
	protected abstract void bootup();
	
	/**
	 * 遥控器软关机。
	 * */
	protected abstract void shutdown();
	
	/**
	 * 向运营商请求节目列表。
	 * */
	protected abstract void requestProgramsList();
	
	public Remote getRemote() {
		return mRemote;
	}
	
	public Detector getDetector() {
		return mDetector;
	}
	
	/**
	 * 接收来自遥控器的事件。
	 * */
	@Override
	public void remote(int keyvalue, boolean isDown) {
		Log.info(TAG, "remote:" + keyvalue + ":" + isDown);
		if(isDown)
			return;
		//Only process key up event.
		// Let the player process the key event.
		mPlayer.keyevent(keyvalue, isDown);
	}
	
}
