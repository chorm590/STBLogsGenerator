package com.chorm.random;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chorm.others.ProgramBeans;
import com.chorm.others.ProgramType;
import com.chorm.others.VideoPixel;
import com.chorm.utils.Log;
import com.chorm.utils.TimeTools;

public class ProgramsGenerator implements Random<ProgramBeans> {
	
	private static final String TAG = "ProgramsGenerator";
	
	private MessageDigest md5; //32位MD5码。
	private char dateMD5[];
	
	private final StringBuilder sb;
	private int recommandedCounter;
	
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
		clear();
		long s = TimeTools.currentSeconds();
		int s1 = mRandom.nextInt(604800) + 12788;
		
		return s - s1;
	}

	private boolean generateIsRecommended() {
		clear();
		final int MAX_RECOMMANEDED = 10;
		boolean re = mRandom.nextBoolean();
		if(re && recommandedCounter < MAX_RECOMMANEDED) {
			recommandedCounter++;
			return true;
		}

		return false;
	}

	private VideoPixel generatePixel() {
		clear();
		int pixel = mRandom.nextInt(3);
		switch(pixel) {
		case 0:
			return VideoPixel.P720;
		case 1:
			return VideoPixel.P1080;
		case 2:
			return VideoPixel.P4K;
		}
		
		return VideoPixel.P720;
	}

	private ProgramType generateProgramType() {
		clear();
		int type = mRandom.nextInt(8);
		switch(type) {
			case 0:
				return ProgramType.KEHUAN;
			case 1:
				return ProgramType.ZONGYI;
			case 2:
				return ProgramType.XIJU;
			case 3:
				return ProgramType.XIQU;
			case 4:
				return ProgramType.GUZHUANG;
			case 5:
				return ProgramType.YANQING;
			case 6:
				return ProgramType.KONGBU;
			case 7:
				return ProgramType.DONGZUO;
		}
		return ProgramType.KEHUAN;
	}

	private int generateDuration() {
		// 返回秒钟数。
		clear();
		//min 38
		//max 12072  --> 3h 21m 12s
		int duration = 0;
		int gravity = 0;
		//控制一下视频长度比重。
		for(int i = 0; i < 10; i++) {
			duration = mRandom.nextInt(12072) + 38;
			//视频长度过短或过长的应占少数。
			gravity = mRandom.nextInt(100);
			if(duration < 300) {
				if(gravity < 5)
					break; //允许有5%的小于5分钟的视频。
			}else if (duration < 2400) {
				if(gravity < 30)
					break; //允许有30%的5 ~ 40分钟的视频。
			} else if(duration < 5400) {
				if(gravity < 80)
					break; //70%的视频 40分钟 ~ 1.5小时。
			}else {
				if(gravity < 5)
					break;
			}
			
//			if(i == 9)
//				Log.info(TAG, "fuck!!!!" +duration);
		}
		
//		return duration;
		return duration * 1000; //Return in millisecond.
	}

	private String generateUrl() {
		clear();
		sb.append("http://203.44.19.4:8976/");
		final int MAX = 18;
		for(int i = 0; i < MAX; i++) {
			sb.append(dateMD5[mRandom.nextInt(dateMD5.length)]);
		}
		sb.append(".m3u8");
		return sb.toString();
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
