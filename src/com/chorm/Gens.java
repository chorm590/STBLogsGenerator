package com.chorm;

import com.chorm.physical.person.Family;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

public class Gens {
	
	private static final String TAG = "Gens";

	public static void main(String[] args) {
		
//		new MyTest().randomTest();
//		new MyTest().programsGenTest();
//		new MyTest().playerTest();
		
		// How many family do your province have?
		Family families[] = new Family[Controls.FAMILY_AMOUNT];
		for(int i = 0; i < families.length; i++) {
			families[i] = new Family();
		}
		
		Log.info(TAG, ">>>>>>> main() end <<<<<<<");
	}
	
}
