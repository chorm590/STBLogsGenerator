package com.chorm.others;

public enum DetectorEvent {
	PLAY_PREPARE  , // >>>>  0  <<<<
	PLAY_START    ,
	PLAY_REPORT   ,
	PLAY_QUIT     , //3
	PLAY_LIVE,
	
	SEEK_START    , //5
	SEEK_END,
	
	PAUSE_MESSAGE , //7
	RESUME_MESSAGE,
	
	BUFFER_START  , //9
	BUFFER_END    ,
	
	BOOTUP,			//11
	SHUTDOWN;
}
