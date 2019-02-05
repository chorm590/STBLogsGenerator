package com.chorm.physical.device;

import com.chorm.detectors.Detector;
import com.chorm.operator.APKOperator;
import com.chorm.operator.ProgramsOperator;
import com.chorm.others.Province;
import com.chorm.physical.player.Player;

/**
 * chorm on 2019-02-03 18:56,havn't have dinner.
 * */
public class STBM301H extends STB {
	
	private static final String TAG = "STBM301H";
	
	public STBM301H(String serial, String mac, String hwv,
			String swv, APKOperator o, Province p,
			Remote r, Player p2, Detector d) {
		super(serial, mac, hwv, swv, o, p, r, p2, d);
		requestProgramsList(); // Generate program list.
	}

	@Override
	protected void bootup() {
		//发送一个开机探针事件。
		mDetector.bootup(serial, mac);
	}

	@Override
	protected void shutdown() {
		mDetector.shutdown(serial, mac);
	}

	@Override
	protected void requestProgramsList() {
		programs = ProgramsOperator.getPrograms();
	}

}
