package com.chorm.physical.person;

import java.util.ArrayList;
import java.util.List;

import com.chorm.physical.device.STB;
import com.chorm.random.RandomTool;
import com.chorm.utils.Controls;

/**
 * 每个家庭都有一个机顶盒。
 * 有N个人。
 * */
public class Family {
	
	private static final String TAG = "Family";

	protected List<Person> members;
	private STB mSTB;
	
	public Family() {
		members = new ArrayList<>();
		membersGens();
		applySTB();
	}

	/**
	 * 向运营商申请机顶盒。
	 * */
	private void applySTB() {
		// TODO Auto-generated method stub
		
	}

	private void membersGens() {
		int memberAmount = RandomTool.randomInt(Controls.FAMILY_MEMBER_MIN, Controls.FAMILY_MEMBER_MAX);
		for(int i = 0; i < memberAmount; i++) {
			
		}
	}
}
