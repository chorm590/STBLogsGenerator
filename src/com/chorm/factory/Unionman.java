package com.chorm.factory;

import java.util.List;

import com.chorm.detectors.Detector;
import com.chorm.detectors.DetectorHangYan;
import com.chorm.detectors.DetectorServerHangYan;
import com.chorm.operator.APKOperator;
import com.chorm.others.Province;
import com.chorm.physical.device.M301HRemote;
import com.chorm.physical.device.Remote;
import com.chorm.physical.device.STB;
import com.chorm.physical.device.STBM301H;
import com.chorm.physical.player.M301HPlayer;
import com.chorm.physical.player.Player;
import com.chorm.random.RandomTool;
import com.chorm.utils.NumericTools;
import com.chorm.utils.TimeTools;

public class Unionman extends STBFactory {

	/**
	 * 9 bytes, in hex character.
	 * AABBCCCCC
	 * 
	 * AA  		-->  manufacture code.
	 * BB		-->  hardware code.
	 * CCCCC	-->  software code. 
	 * */
	private static final String MANUFACTURE_CODE = "37060002A";
	
	private static final String AREA_CODE = "F3";
	
	private final StringBuilder sb = new StringBuilder();
	
	@Override
	public List<STB> createSTB(int amount) {
		STB stb;
		for(int i = 0; i < amount; i++) {
			stb = new STBM301H(createSerial(stbList), createMac(stbList), createHardwareVersion(),
					createSoftwareVersion(), whatIsYourAPKOperator(), whichProvince(),
					adaptRemote(), adaptPlayer(), prebuildDetector());
			//Register remote register.
			((M301HRemote)(stb.getRemote())).registerSTBListener(stb);
			//Register STB to player.
			((M301HPlayer)(stb.getPlayer())).setSTB(stb);
			// Register STB serial and mac.
			stb.getDetector().registerSerialMac(stb.getSerial(), stb.getMac());
			stbList.add(stb);
		}
		return stbList;
	}

	@Override
	protected String createSerial(List<STB> stbList) {
		// 32 开头。 总共32位 [0 - 9] [A - Z]
		clear();
		sb.append("32");
		int index;
		for(int i = 0; i < 30; i++) {
			index = RandomTool.randomInt(0, 36);
			if(index < 10) {
				sb.append(index);
			}else {
				sb.append((char)Integer.parseInt(String.valueOf(index - 10 + 'A')));
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	protected String createMac(List<STB> stbList) {
		clear();
		sb.append("000C27");
		int index;
		for(int i = 0; i < 6; i++) {
			index = RandomTool.randomInt(0, 16);
			if(index < 10) {
				sb.append(index);
			}else {
				sb.append((char)Integer.parseInt(String.valueOf(index - 10 + 'A')));
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	protected String createHardwareVersion() {
		return "M301H_UM_HB";
	}

	@Override
	protected String createSoftwareVersion() {
		clear();
		sb.append("WLW-301-JL.0");
		sb.append(RandomTool.randomInt(0, 100));
		sb.append(".");
		sb.append(TimeTools.getDate2());
		sb.append("-HBH-H");

		return sb.toString();
	}

	@Override
	protected APKOperator whatIsYourAPKOperator() {
		return APKOperator.BESTONTV;
	}

	@Override
	protected Province whichProvince() {
		return Province.HUBEI;
	}

	@Override
	protected Remote adaptRemote() {
		return new M301HRemote();
	}

	@Override
	protected Player adaptPlayer() {
		return new M301HPlayer();
	}

	@Override
	protected Detector prebuildDetector() {
		DetectorServerHangYan.getInstance().getDetectorInfo().setApkCode(DetectorHangYan.CODE);
		DetectorServerHangYan.getInstance().getDetectorInfo().setAreaCode(AREA_CODE + NumericTools.int2String(RandomTool.randomPercentage()));
		DetectorServerHangYan.getInstance().getDetectorInfo().setManufactureCode(MANUFACTURE_CODE);
		return new DetectorHangYan();
	}

	private void clear() {
		sb.delete(0, sb.length());
	}
}
