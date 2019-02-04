package com.chorm.physical.person;

import java.util.HashSet;
import java.util.Set;

import com.chorm.others.ProgramType;
import com.chorm.physical.device.STB;

public class Person {
	
	private static final String TAG ="Person";
	
	private Set<ProgramType> favLabels = new HashSet<>();
	private Family mFamily;
	
	public Person(Family family) {
		mFamily = family;
	}

	/**
	 * 每一个人都有他们的机顶盒子。
	 * 都可以操纵他们的机顶盒子。
	 * */
	public STB getSTB() {
		
	}
	
	public void setFavLabel() {
		
	}
	
	public Set<ProgramType> getFavLabel(){
		return favLabels;
	}

}
