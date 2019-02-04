package com.chorm.physical.player;

import java.util.Timer;
import java.util.TimerTask;

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
	
	public void setSTB(STB stb) {
		this.stb = stb;
	}
	
	private enum StateMachine {
		PREPARED,
		PLAYING,
		PAUSED,
		SEEKING,
		BUFFERING,
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
				if(sm == StateMachine.SEEKING) {
					start(currentPb, currentPb.getCurrentPosition());
				}else {
					play();
				}
			}break;
			default:{
				Log.info(TAG, "Invalid keyevent:" + keyvalue + ",down:" + isDown);
			}break;
		}
	}

	private void play() {
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
		if(sm == StateMachine.PREPARED ||
				sm == StateMachine.SEEKING) {
			counter = 0;
			//Go to play video.
			if(mTimer != null)
				mTimer.cancel(); //Guarantee only 1 TimerTask running in one player daemon.
			mTimer = new Timer();
			mTimer.schedule(this, 256, 1000);
			pb.setCurrentPosition(position);
			currentPb = pb;
			Log.info(TAG, pb.getName() + ":" + pb.getUrl() + " is playing...");
		}
	}

	@Override
	public void prepare(ProgramBeans pb) {
		sm = StateMachine.PREPARED;
		stb.getDetector().playPrepare(pb);
	}

	@Override
	public void start(ProgramBeans pb, int position) {
		playVideo(pb, position);
		sm = StateMachine.PLAYING;
		stb.getDetector().playStart(pb, position);
	}

	@Override
	public void quit(ProgramBeans pb) {
		sm = StateMachine.STOPPED;
		stb.getDetector().playQuit(pb, currentPb.getCurrentPosition());
	}

	@Override
	public void seek(ProgramBeans pb) {
		if(sm == StateMachine.PLAYING) {
			sm = StateMachine.SEEKING;
			mTimer.cancel();
			mTimer = null;
			mTimer = new Timer();
			mTimer.schedule(new SeekTimerTask(), 0, 1000);
			stb.getDetector().seekStart(pb, currentPb.getCurrentPosition());
			Log.info(TAG, "Seeking...");
		}
	}

	@Override
	public void pause(ProgramBeans pb) {
		sm = StateMachine.PAUSED;
	}

	@Override
	public void resume(ProgramBeans pb) {
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
		currentPb.setCurrentPosition(currentPb.getCurrentPosition() + 1000);
		if(currentPb.getCurrentPosition() >= currentPb.getDuration()) {
			// Current video end.
			currentPb.setCurrentPosition(currentPb.getDuration()); //Gracefully report to detector server.
			Log.info(TAG, "duration:" + Integer.toString(currentPb.getCurrentPosition()));
			quit(currentPb);
			mTimer.cancel();
			mTimer = null;
		}else {
			Log.info(TAG, "duration:" + Integer.toString(currentPb.getCurrentPosition()));
		}
	}
	
	private class SeekTimerTask extends TimerTask {

		@Override
		public void run() {
			// 3 seconds per seek time.
			currentPb.setCurrentPosition(currentPb.getCurrentPosition() + 3000);
			Log.info(TAG, "SeekTimerTask," + currentPb.getCurrentPosition());
			if(currentPb.getCurrentPosition() >= currentPb.getDuration()) {
				currentPb.setCurrentPosition(currentPb.getDuration());
				// Force end the seek state.
				mTimer.cancel();
				mTimer = null;
				Log.info(TAG, "Seek to end of the video." + currentPb.getCurrentPosition());
			}
		}
		
	}

}
