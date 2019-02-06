package com.chorm.physical.person;

import java.util.ArrayList;
import java.util.List;

import com.chorm.operator.CMCCSTBOperator;
import com.chorm.physical.device.STB;
import com.chorm.random.RandomTool;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

/**
 * 每个家庭都有一个机顶盒。
 * 有N个人。
 * 
 * Family向运营商申请机顶盒。
 * 然后 Family members 打开机顶盒看视频，
 * 然后我们的故事就此开始。。。
 * chorm marked on 2019-02-05 00:27.
 * */
public class Family {
	
	private static final String TAG = "Family";

	protected List<Person> members;
	private STB mSTB;
	private STBManager stbManager;
	
	public Family() {
		members = new ArrayList<>();
		stbManager = new STBManager();
		membersGens();
		applySTB();
	}

	/**
	 * 向运营商申请机顶盒。
	 * */
	private void applySTB() {
		mSTB = CMCCSTBOperator.getInstance().applySTB(this);
	}

	private void membersGens() {
		int memberAmount = RandomTool.randomInt(Controls.FAMILY_MEMBER_MIN, Controls.FAMILY_MEMBER_MAX);
//		Log.info(TAG, "member amount:" + memberAmount);
		Person mPerson;
		for(int i = 0; i < memberAmount; i++) {
			mPerson = new Person(this);
			members.add(mPerson);
		}
	}
	
	/**
	 * Must default permission.
	 * 检查家里机顶盒的使用情况。只有当家里的机顶盒无人使用时，才能申请成功。
	 * */
	STB useSTB(Person who) {
		if(stbManager.isUsing) {
			return null;
		}else {
			stbManager.isUsing = true;
			stbManager.whoUsing = members.indexOf(who);
			return mSTB;
		}
	}
	
	/**
	 * Must default permission.
	 * 家里某人不看电视了。
	 * */
	void releaseSTB(Person who) {
		if(stbManager.whoUsing == members.indexOf(who)) {
			stbManager.isUsing = false;
			stbManager.whoUsing = -1;
		}
	}
	
	/**
	 * chorm create it on 2019-02-05 10:41. Happy spring festival.
	 */
	private class STBManager{
		/**
		 * 家里的机顶盒是否在使用中。
		 * */
		boolean isUsing;
		/**
		 * 记录家庭成员编号。List中的Index。
		 * */
		int whoUsing = -1;
	}
}
