package com.chorm.random;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chorm.others.ProgramBeans;
import com.chorm.others.ProgramType;
import com.chorm.others.VideoPixel;
import com.chorm.utils.TimeTools;

public class ProgramsGenerator implements Random<ProgramBeans> {
	
	private static final String TAG = "ProgramsGenerator";
	
	private MessageDigest md5; //32位MD5码。
	private char dateMD5[];
	
	private final StringBuilder sb;
	
	public ProgramsGenerator() {
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(TimeTools.getDate().getBytes());
			dateMD5 = new BigInteger(1, md5.digest()).toString(16).toCharArray();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		sb = new StringBuilder();
	}

	@Override
	public ProgramBeans generate() {
		ProgramBeans pb = new ProgramBeans();
		
		pb.setName(generateName());
		pb.setUrl(generateUrl());
		pb.setDuration(generateDuration());
		pb.setPgtype(generateProgramType());
		pb.setPixel(generatePixel());
		pb.setRecommended(generateIsRecommended());
		pb.setUpLineTime(generateUpLineTime());
		
		return pb;
	}

	private long generateUpLineTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean generateIsRecommended() {
		// TODO Auto-generated method stub
		return false;
	}

	private VideoPixel generatePixel() {
		// TODO Auto-generated method stub
		return null;
	}

	private ProgramType generateProgramType() {
		// TODO Auto-generated method stub
		return null;
	}

	private int generateDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	private String generateUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateName() {
		clear();
		sb.append("program ");
		final int MAX = 5;
		int suffixLen = mRandom.nextInt(MAX) + 1;
		for(int i = 0; i < suffixLen; i++) {
			sb.append(dateMD5[mRandom.nextInt(dateMD5.length)]);
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private void clear() {
		sb.delete(0, sb.length());
	}

}
