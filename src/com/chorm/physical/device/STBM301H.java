package com.chorm.physical.device;

import com.chorm.others.Operator;
import com.chorm.others.Province;
import com.chorm.physical.player.Player;

public class STBM301H extends STB {
	
	private static final String TAG = "STBM301H";
	
	protected STBM301H(String serial, String mac, String hwv, String swv, Operator o, Province p, Remote r, Player p2) {
		super(serial, mac, hwv, swv, o, p, r, p2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bootup() {
		// TODO Auto-generated method stub
		//发送一个开机探针事件。
		
	}

	@Override
	protected void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void requestProgramsList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRemote() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerPlayer() {
		// TODO Auto-generated method stub
		
	}

}
