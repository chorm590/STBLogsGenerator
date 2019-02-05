package com.chorm.physical.player;

//import java.util.TimerTask;

import com.chorm.others.PlayerStateMachine;

public abstract class PlayerBase /*extends TimerTask*/ implements Player {
	/**
	 * 播放器状态机。
	 * */
	protected PlayerStateMachine sm = PlayerStateMachine.STOPPED;
	
	public PlayerStateMachine getPlayerStateMachine() {
		return sm;
	}
}
