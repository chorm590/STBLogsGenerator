package com.chorm.random;

public abstract class RandomBase implements Random {

	protected java.util.Random jutilRandom;
	
	protected RandomBase() {
		jutilRandom = new java.util.Random();
	}
}
