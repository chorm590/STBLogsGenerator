package com.chorm.random;

import java.util.Random;

public class RandomTool {

	/**
	 * @return return a random int number in [downlimit, uplimit).
	 * */
	public static int randomInt(int downlimit, int uplimit) {
		Random mRandom = new Random();
		return mRandom.nextInt(uplimit) + downlimit;
	}
	
	/**
	 * @return return a value between 0 ~ 100. [0,100].
	 * */
	public static int randomPercentage() {
		Random mRandom = new Random();
		return mRandom.nextInt(101);
	}
}
