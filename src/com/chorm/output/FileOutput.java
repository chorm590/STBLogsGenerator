package com.chorm.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Output the data that in memory to disk..
 * */
public final class FileOutput {

	private static final String TAG = "FileOutput";
	
	private static final String DEFAULT_OUTPUT_FILE_NAME = "default.file";
	
	private static File mFile;
	private static FileOutputStream fos;
	
	private static void init(String fn) {
		if(fn == null || fn.equals("")) {
			fn = "out/" + DEFAULT_OUTPUT_FILE_NAME;
		}
		
		mFile = new File(fn);
		if(fos != null)
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		try {
			fos = new FileOutputStream(mFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void release() {
		mFile = null;
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fos = null;
		}
	}
	
	
	public static void output(String data) {
		init(null);
		
		try {
			fos.write(data.getBytes(), 0, data.getBytes().length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		release();
	}
}
