package com.chorm;

import com.chorm.physical.person.Family;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

public class Gens {
	
	private static final String TAG = "Gens";
	
	public static int familiesAmount;

	public static void main(String[] args) {
		
//		new MyTest().randomTest();
//		new MyTest().programsGenTest();
//		new MyTest().playerTest();
		
		// How many family do your province have?
		
		Family families[];
		System.out.println("args length:" + args.length);
		if(args.length == 1) {
			int amount = Integer.parseInt(args[0]);
			families = new Family[amount];
		}else {
			families = new Family[Controls.FAMILY_AMOUNT];
		}
		familiesAmount = families.length;
		for(int i = 0; i < families.length; i++) {
			families[i] = new Family();
		}
		
		Log.info(TAG, ">>>>>>> main() end <<<<<<<");
	}
	
}
