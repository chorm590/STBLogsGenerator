package com.chorm.physical.player;

import java.util.Timer;

import com.chorm.others.M301HKeyEvent;
import com.chorm.others.ProgramBeans;
import com.chorm.physical.device.STB;
import com.chorm.utils.Log;

/**
 * HuBei M301H.
 * */
public class M301HPlayer extends PlayerBase {
	
	private static final String TAG = "M301HPlayer";
	
	/**
	 * 没有什么实际意义的恶趣味计数器变量。
	 * */
	private int counter;
	
	private STB stb;
	/**
	 * 当前正在播放的视频。
	 * */
	private ProgramBeans currentPb;
	/**
	 * 播放器状态机。
	 * */
	private StateMachine sm = StateMachine.STOPPED;
	private Timer mTimer;
	
	public M301HPlayer(STB stb) {
		this.stb = stb;
	}
	
	private enum StateMachine {
		PREPARED,
		PLAYING,
		PAUSED,
		STOPPED;
	}

	@Override
	public void keyevent(int keyvalue, boolean isDown) {
		/*
		 * 用户操作遥控器，遥控器事件传给机顶盒，机顶盒将遥控器事件原样传递给这个播放器。
		 * 最后由播放器来解析具体的遥控器事件并作出响应。
		 * */
		if(isDown)
			return; //Don't acknowledge press down event.
		switch(M301HKeyEvent.parse(keyvalue)) {
			case KEYCODE_DOWN:{
				// Do nothing...
			}break;
			case KEYCODE_UP:{
				// Do nothing...
			}break;
			case KEYCODE_LEFT:{
				// Do nothing...
			}break;
			case KEYCODE_RIGHT:{
				// Do nothing...
			}break;
			case KEYCODE_CENTER:{
				// Pick a program to play... hehe.
				play();
			}break;
			default:{
				Log.info(TAG, "Invalid keyevent:" + keyvalue + ",down:" + isDown);
			}break;
		}
	}

	private void play() {
		// TODO Auto-generated method stub
		
		// 1. pick a program.
		
		// 2. prepare to play.
		
		// 3. start play,pick play position.
		
		// 4. I don't know...
	}
	
	/**
	 * 开始播放视频啊。
	 * @param position 
	 * */
	private void playVideo(ProgramBeans pb, int position) {
		if(sm != StateMachine.PREPARED) {
			return;
		}
		counter = 0;
		//Go to play video.
		if(mTimer != null)
			mTimer.cancel(); //Guarantee only 1 TimerTask running in one player daemon.
		mTimer = new Timer();
		mTimer.schedule(this, 256, 1000);
		pb.setCurrentDuration(position);
		currentPb = pb;
		Log.info(TAG, pb.getName() + ":" + pb.getUrl() + " is playing...");
	}

	@Override
	public void prepare(ProgramBeans pb) {
		sm = StateMachine.PREPARED;
//		stb.getDetector().playPrepare(pb);
	}

	@Override
	public void start(ProgramBeans pb, int position) {
		playVideo(pb, position);
		sm = StateMachine.PLAYING;
//		stb.getDetector().playStart(pb, position);
	}

	@Override
	public void quit(ProgramBeans pb) {
		sm = StateMachine.STOPPED;
//		stb.getDetector().playQuit(pb, currentPb.getCurrentDuration());
	}

	@Override
	public void seek(ProgramBeans pb) {
		// TODO Auto-generated method stub
		sm = StateMachine.PAUSED;
//		stb.getDetector().see
	}

	@Override
	public void pause(ProgramBeans pb) {
		// TODO Auto-generated method stub
		sm = StateMachine.PAUSED;
	}

	@Override
	public void resume(ProgramBeans pb) {
		// TODO Auto-generated method stub
		sm = StateMachine.PREPARED;
	}
	
	private void buffer(ProgramBeans pb) {
		sm = StateMachine.PAUSED;
	}
	
	/**
	 * 把它当成TimerTask来用就好啦。
	 * chorm on 2019-02-04 11:01.
	 * */
	@Override
	public void run() {
		Log.info(TAG, "Playing " + currentPb.getName() + "," + Integer.toString(counter++));
		currentPb.setCurrentDuration(currentPb.getCurrentDuration() + 1000);
		if(currentPb.getCurrentDuration() >= currentPb.getDuration()) {
			// Current video end.
			currentPb.setCurrentDuration(currentPb.getDuration()); //Gracefully report to detector server.
			quit(currentPb);
			mTimer.cancel();
			mTimer = null;
		}else {
			Log.info(TAG, "duration:" + Integer.toString(currentPb.getCurrentDuration()));
		}
	}

}
