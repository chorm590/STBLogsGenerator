package com.chorm;

import java.io.File;
import java.io.IOException;

import com.chorm.physical.person.Family;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

import junit.MyTest;

public class Gens {
	
	private static final String TAG = "Gens";
	
	public static int familiesAmount;

	public static void main(String[] args) throws Exception {
		
//		new MyTest().randomTest();
//		new MyTest().programsGenTest();
//		new MyTest().playerTest();
//		new MyTest().testfosequence();
		
//		System.out.println(new File("/var/bigdata/j/k/l/abc").mkdirs());
		
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
