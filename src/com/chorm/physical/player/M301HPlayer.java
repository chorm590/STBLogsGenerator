package com.chorm.physical.player;

import com.chorm.others.M301HKeyEvent;
import com.chorm.others.ProgramBeans;
import com.chorm.physical.device.STB;
import com.chorm.utils.Log;

/**
 * HuBei M301H.
 * */
public class M301HPlayer implements Player {
	
	private static final String TAG = "M301HPlayer";
	
	private STB stb;
	
	public M301HPlayer(STB stb) {
		this.stb = stb;
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
				
			}break;
			case KEYCODE_UP:{
				
			}break;
			case KEYCODE_LEFT:{
				
			}break;
			case KEYCODE_RIGHT:{
				
			}break;
			case KEYCODE_CENTER:{
				
			}break;
			case KEYCODE_POWER:{
				
			}break;
			default:{
				Log.info(TAG, "Invalid keyevent:" + keyvalue + ",down:" + isDown);
			}break;
		}
	}

	@Override
	public void prepare(ProgramBeans pb) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void start(ProgramBeans pb, int position) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void quit(ProgramBeans pb) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void seek(ProgramBeans pb) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void pause(ProgramBeans pb) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void resume(ProgramBeans pb) {
		// TODO Auto-generated method stub
		
	}
	
	private void buffer(ProgramBeans pb) {
		
	}

}
