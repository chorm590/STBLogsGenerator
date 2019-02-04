package com.chorm.detectors;

import com.chorm.others.DetectorEvent;
import com.chorm.others.DetectorType;
import com.chorm.others.ProgramBeans;
import com.chorm.utils.NumericTools;
import com.chorm.utils.TimeTools;

/**
 * 适用于湖北M301H的杭研探针系统。
 * */
public class DetectorHangYan extends DetectorBase {
	
	
	public DetectorHangYan() {
		super(DetectorType.HANGYAN);
		serverUrl = "/home/chorm/detector/" + TimeTools.getDate() + "/"; //Just a directory.
	}

	@Override
	public void bootup(String serial, String mac) {
		/*
规范：事件类型|serial|mac|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.BOOTUP.ordinal()));
		append(serial);
		append(mac);
		report();
	}
	
	@Override
	public void shutdown(String serial, String mac) {
		/*
规范：事件类型|serial|mac|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.SHUTDOWN.ordinal()));
		append(serial);
		append(mac);
		report();
	}

	@Override
	public void playPrepare(ProgramBeans pb) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|节目画面质量|节目分类|是否首页推荐|首页推荐上线时间|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_PREPARE.ordinal()));
		programWrapper(pb);
		report();
	}

	@Override
	public void playStart(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|节目画面质量|节目分类|是否首页推荐|首页推荐上线时间|播放位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_START.ordinal()));
		programWrapper(pb);
		append(Integer.toString(position));
		report();
	}

	@Override
	public void playQuit(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|节目画面质量|节目分类|是否首页推荐|首页推荐上线时间|播放位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		programWrapper(pb);
		append(Integer.toString(position));
		report();
	}

	@Override
	public void playReport(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|播放位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(position));
		report();
	}

	@Override
	public void seekStart(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|播放位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(position));
		report();
	}

	@Override
	public void seekEnd(ProgramBeans pb, int startPos, int endPos) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|起始位置|终止位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(startPos));
		append(Integer.toString(endPos));
		report();
	}

	@Override
	public void bufferStart(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(position));
		report();
	}

	@Override
	public void bufferEnd(ProgramBeans pb) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		report();
	}

	@Override
	public void pauseMessage(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(position));
		report();
	}

	@Override
	public void resumeMessage(ProgramBeans pb, int position) {
		/*
规范：事件类型|视频URL|节目名称|节目时长|位置|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(Integer.toString(position));
		report();
	}

	@Override
	public void playLive(ProgramBeans pb) {
		/*
规范：事件类型|
		 * */
		append(NumericTools.int2hexString(DetectorEvent.PLAY_QUIT.ordinal()));
		report();
	}

	private void programWrapper(ProgramBeans pb) {
		append(pb.getUrl());
		append(pb.getName());
		append(Integer.toString(pb.getDuration()));
		append(NumericTools.int2hexString(pb.getPixel().ordinal()));
		append(NumericTools.int2hexString(pb.getPgtype().ordinal()));
		append(Boolean.toString(pb.isRecommended()));
		append(Long.toString(pb.getUpLineTime()));
	}

}
