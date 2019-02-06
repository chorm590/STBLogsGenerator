package com.chorm.others;

public enum DetectorEvent {
	PLAY_PREPARE  ,
	PLAY_START    ,
	SEEK_START    ,
	SEEK_END,
	PAUSE_MESSAGE ,
	RESUME_MESSAGE, //4
	BUFFER_START  ,
	BUFFER_END    ,
	PLAY_QUIT     , //7
	PLAY_REPORT   ,
	BOOTUP,
	SHUTDOWN, //10
	PLAY_LIVE;
}
