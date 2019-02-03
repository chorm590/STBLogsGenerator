package com.chorm.detectors;

import com.chorm.others.ProgramBeans;

/**
 * 探针软件。
 * 用于生成探针事件并上报。
 * */
public interface Detector {
	
	char SEPARATOR = '|';

	void report();
	
	void bootup(ProgramBeans pb);
	
	void playPrepare(ProgramBeans pb);
	
	void playStart(ProgramBeans pb, int position);
	
	void playQuit(ProgramBeans pb, int position);
	
	void playReport(ProgramBeans pb, int position);
	
	void seekStart(ProgramBeans pb, int position);
	
	void seekEnd(ProgramBeans pb, int startPos, int endPos);
	
	void bufferStart(ProgramBeans pb, int position);
	
	void bufferEnd(ProgramBeans pb);
	
	void pauseMessage(ProgramBeans pb, int position);
	
	void resumeMessage(ProgramBeans pb, int position);
	
	void playLive(ProgramBeans pb);
}
