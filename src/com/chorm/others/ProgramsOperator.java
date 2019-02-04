package com.chorm.others;

import java.util.HashSet;
import java.util.Set;

import com.chorm.random.ProgramsGenerator;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;

/**
 * 节目提供商。
 * 专门向机顶盒提供视频服务。
 * */
public class ProgramsOperator {
	
	private static final String TAG = "ProgramsOperator";

	private static Set<ProgramBeans> programs = new HashSet<>();
	
	static {
		ProgramsGenerator pg = new ProgramsGenerator();
		for(int i = 0; i < Controls.PROGRAMS_AMOUNT_LIMIT; i++) {
			programs.add(pg.generate()); //pg name重复的节目不会被添加。
		}
//		Log.info(TAG, "Programs generated! " + programs.size());
//		for(ProgramBeans pb : programs) {
//			Log.info(TAG, pb.toString());
//		}
	}
	
	public static Set<ProgramBeans> getPrograms(){
		return programs;
	}
}
