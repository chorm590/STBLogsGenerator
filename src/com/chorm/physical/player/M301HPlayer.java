package com.chorm.physical.player;

import java.util.Timer;
import java.util.TimerTask;

import com.chorm.others.M301HKeyEvent;
import com.chorm.others.PlayerStateMachine;
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
	
	private Timer mTimer;
	
	public void setSTB(STB stb) {
		this.stb = stb;
	}
	
	@Override
	public void keyevent(int keyvalue, boolean isDown, Object obj) {
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
				// Seek to right side.
				if(sm == PlayerStateMachine.PLAYING) {
					seek(currentPb);
				}
			}break;
			case KEYCODE_CENTER:{
				if(sm == PlayerStateMachine.SEEKING) {
//					start(currentPb, currentPb.getCurrentPosition());
					playVideo(currentPb, currentPb.getCurrentPosition());
					stb.getDetector().seekEnd(currentPb, currentPb.getCurrentPosition());
				}else { // Pick a program to play... hehe.
					ProgramBeans pb = (ProgramBeans) obj;
					Log.info(TAG, ">>>>>> start to play:" + pb.getName() + " > "
							+ pb.getUrl() + " > " +pb.getDuration());
					prepare(pb);
					start(pb, 0);
				}
			}break;
			case KEYCODE_BACK:{
				quit(currentPb);
			}break;
			default:{
				Log.info(TAG, "Invalid keyevent:" + keyvalue + ",down:" + isDown);
			}break;
		}
	}

	/**
	 * 开始播放视频啊。
	 * @param position 
	 * */
	private void playVideo(ProgramBeans pb, int position) {
		if(sm == PlayerStateMachine.PREPARED ||
				sm == PlayerStateMachine.SEEKING) {
			counter = 0;
			//Go to play video.
			if(mTimer != null) {
				mTimer.cancel(); //Guarantee only 1 TimerTask running in one player daemon.
			}
			mTimer = new Timer();
			mTimer.schedule(new PlayTimerTask(), 256, 1000);
			pb.setCurrentPosition(position);
			currentPb = pb;
			Log.info(TAG, pb.getName() + ":" + pb.getUrl() + " is playing...");
		}else {
			throw new IllegalStateException("Current isn't PREPARE or SEEKING state, can't play it.");
		}
	}

	@Override
	public void prepare(ProgramBeans pb) {
		sm = PlayerStateMachine.PREPARED;
		stb.getDetector().playPrepare(pb);
	}

	@Override
	public void start(ProgramBeans pb, int position) {
		playVideo(pb, position);
		sm = PlayerStateMachine.PLAYING;
		stb.getDetector().playStart(pb, position);
	}

	@Override
	public void quit(ProgramBeans pb) {
		sm = PlayerStateMachine.STOPPED;
//		Log.info(TAG, "stb:" + stb + ",pb:" + pb + ",currentPb:" + currentPb);
		stb.getDetector().playQuit(pb, pb.getCurrentPosition());
	}

	@Override
	public void seek(ProgramBeans pb) {
		if(pb == null)
			return;
		
		if(sm == PlayerStateMachine.PLAYING) {
			sm = PlayerStateMachine.SEEKING;
			if(mTimer != null)
				mTimer.cancel(); // Cancel the PlayTimerTask,begin to task SeekTimerTask.
			mTimer = new Timer();
			mTimer.schedule(new SeekTimerTask(), 0, 1000);
			stb.getDetector().seekStart(pb, currentPb.getCurrentPosition());
			Log.info(TAG, "Seeking...");
		}
	}

	@Override
	public void pause(ProgramBeans pb) {
		sm = PlayerStateMachine.PAUSED;
	}

	@Override
	public void resume(ProgramBeans pb) {
		sm = PlayerStateMachine.PREPARED;
	}
	
	private void buffer(ProgramBeans pb) {
		// TODO 
	}
	
	private class PlayTimerTask extends TimerTask {
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
