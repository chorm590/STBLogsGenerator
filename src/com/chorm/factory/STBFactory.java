package com.chorm.factory;

import java.util.ArrayList;
import java.util.List;

import com.chorm.detectors.Detector;
import com.chorm.operator.APKOperator;
import com.chorm.others.Province;
import com.chorm.physical.device.Remote;
import com.chorm.physical.device.STB;
import com.chorm.physical.player.Player;

public abstract class STBFactory {
	
	protected List<STB> stbList;
	
	public STBFactory() {
		stbList = new ArrayList<>();
	}

	public abstract List<STB> createSTB(int amount);
	
	protected abstract String createSerial(List<STB> stbList);
	
	protected abstract String createMac(List<STB> stbList);
	
	protected abstract String createHardwareVersion();
	
	protected abstract String createSoftwareVersion();
	
	protected abstract APKOperator whatIsYourAPKOperator();
	
	protected abstract Province whichProvince();
	
	protected abstract Remote adaptRemote();
	
	protected abstract Player adaptPlayer();
	
	protected abstract Detector prebuildDetector();
	
}
