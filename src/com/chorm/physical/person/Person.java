package com.chorm.physical.person;

import java.util.HashSet;
import java.util.Set;

import com.chorm.others.ProgramType;
import com.chorm.random.RandomTool;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

public class Person {
	
	private static final String TAG ="Person";
	
	private Set<ProgramType> favLabels = new HashSet<>();
	private Family mFamily;
	
	public Person(Family family) {
		Log.info(TAG, "newwing Person...");
		mFamily = family;
		setFavLabel();
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

}
