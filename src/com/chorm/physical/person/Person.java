package com.chorm.physical.person;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.chorm.others.M301HKeyEvent;
import com.chorm.others.PlayerStateMachine;
import com.chorm.others.ProgramBeans;
import com.chorm.others.ProgramType;
import com.chorm.physical.device.Remote;
import com.chorm.physical.device.STB;
import com.chorm.physical.player.PlayerBase;
import com.chorm.random.RandomTool;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

public class Person {
	
	private final String TAG;
	
	private boolean isWatching;
	
	private Set<ProgramType> favLabels = new HashSet<>();
	private Family mFamily;
	private Timer watchTVThread;
	private STB stb;
	
	public Person(Family family) {
		TAG = "Person 0x" + Integer.toHexString(this.hashCode());
		Log.info(TAG, "newing Person...");
		mFamily = family;
		setFavLabel();
		//Do you want to watch tv?
		watchTVThread = new Timer();
		watchTVThread.schedule(new WatchTV(), RandomTool.randomInt(30000, 120000)/*30s~120s*/, 5000/*5s*/);
	}

	/**
	 * What's your labels..?
	 * chorm,2019-02-05 10:46
	 * */
	public void setFavLabel() {
		int labelAmount = RandomTool.randomInt(Controls.PERSON_FAV_LABEL_MIN, Controls.PERSON_FAV_LABEL_MAX);
		
		for(int i = 0; i < labelAmount; i++) {
			// Pick a program type in random.
			favLabels.add(ProgramType.getProgramType(RandomTool.randomInt(0, 8)));
		}
		
		if(RandomTool.randomPercentage() < 5) {
			favLabels.add(null); //null fav label mean can access any type of program.
		}
		
		Log.info(TAG, "Favourite label amount:" + labelAmount);
	}
	
	public Set<ProgramType> getFavLabel(){
		return favLabels;
	}
	
	public void watchtv(STB stb) {
		if(stb == null) {
			Log.info(TAG, "The STB was using...");
			return;
		}
		
		this.stb = stb;
		Remote mRemote = stb.getRemote();
		if(!stb.isRuning()) {
			Log.info(TAG, "Power on the stb.");
			stb.powerOn();
		}
		Log.info(TAG, "Going to watch tv.");

		//Choose a program by your favorite label and play it.
		mRemote.key(M301HKeyEvent.KEYCODE_CENTER.ordinal(), chooseProgram());
		isWatching = true;
	}
	
	private ProgramBeans chooseProgram() {
		ProgramBeans pb = null;
		
		Iterator<ProgramType> mIterator = favLabels.iterator();
		int favLabelAmount = favLabels.size();
		int whichOne = RandomTool.randomInt(0, favLabelAmount);
		Log.info(TAG, "favLabelAmount:" + favLabelAmount + ",whichOne:" + whichOne);
		ProgramType pgtype = null;
		for(int i = 0; i < whichOne; i ++) {
			pgtype = mIterator.next();
		}
		Log.info(TAG, "Random choose ProgramType:" + pgtype);
		
		// Choose a program from the STB program list by this pgtype.
		Set<ProgramBeans> programs = stb.getPrograms();
		Iterator<ProgramBeans> mIterator2 = programs.iterator();
		if(pgtype == null) { // All ok!!!
			whichOne = RandomTool.randomInt(0, programs.size());
			for(favLabelAmount = 0; favLabelAmount <= whichOne; favLabelAmount++) {
				pb = mIterator2.next();
			}
		}else {
			ProgramBeans pbs[] = new ProgramBeans[programs.size()];
			whichOne = 0; // Use it as 'index' of the array.
			while(mIterator2.hasNext()) {
				pb = mIterator2.next();
				if(pb.getPgtype() == pgtype) {
					pbs[whichOne++] = pb;
				}
			}
			
			pb = pbs[RandomTool.randomInt(0, whichOne)];
		}
		
		return pb;
	}

	private class WatchTV extends TimerTask {
		
		PlayerStateMachine psm;

		@Override
		public void run() {
			Log.info(TAG, "WatchTV,isWatching:" + isWatching);
			if(!isWatching) {
				if(RandomTool.randomPercentage() < 20) { // 20% probability wanna watch tv.
					Person.this.watchtv(Person.this.mFamily.useSTB(Person.this));
				}
			}else {
				//当前播放器的状态。
				psm = ((PlayerBase)(Person.this.stb.getPlayer())).getPlayerStateMachine();
				Log.info(TAG, "playerstatemachine:" + psm);
				
				// -- 1 -- End seek.
				if(psm == PlayerStateMachine.SEEKING
						&& RandomTool.randomPercentage() < 70) { // 70% probability end seeking...
					Person.this.stb.getRemote().key(M301HKeyEvent.KEYCODE_CENTER.ordinal(), null); // End the seeking.
				}
				// -- 2-- quit
				else if(psm == PlayerStateMachine.PLAYING
						&& RandomTool.randomPercentage() < 50) { //50% probability want to release tv.
					Log.info(TAG, "Quit the program.");
					Person.this.stb.getRemote().key(M301HKeyEvent.KEYCODE_BACK.ordinal(), null); //quit the program.
					isWatching = false;
					// Release the stb using.
					mFamily.releaseSTB(Person.this);
				}
				// -- 3 -- seek
				else if(psm == PlayerStateMachine.PLAYING
						&& RandomTool.randomPercentage() < 7) {
					Log.info(TAG, "Seek the program.");
					Person.this.stb.getRemote().key(M301HKeyEvent.KEYCODE_RIGHT.ordinal(), null); //seek to right side.
				}
				// otherwise
				else {
					// Do nothing.
				}
			}
			
		}
		
	}

}
