package com.chorm.output;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.chorm.others.CompressType;
import com.chorm.random.RandomTool;
import com.chorm.utils.Controls;
import com.chorm.utils.Log;
import com.chorm.utils.TimeTools;

/**
 * 定义将日志输出到文件的一些规范。
 * 子类务必设计成单例模式。
 * */
public abstract class FileOutput {
	
	private static final String TAG = "FileOutput";

	/**
	 * 输出文件单个尺寸最大值。
	 * 64MB  -->  2019-02-06 19:41
	 * */
	protected static final int FILE_SIZE_MAXIMUM = Controls.FILE_OUTPUT_SIZE_MAXIMUM;
	private static int sequence;
	protected static int fileSize; //注意控制单个文件不得超过2G。
	protected static String dir;
//	protected static boolean is
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	
	protected FileOutput(String fnPrefix, CompressType compressType, String storagePath) {
		dir = storagePath + "/" + TimeTools.getDate3();
		checkLogsDir();
		streamMaker(fnPrefix);
	}
	
	public abstract void write(String line);
	
	protected void closeStream() {
		try {
			bos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			bos = null;
			fos = null;
		}
	}
	
	protected BufferedOutputStream getBufferedOutputStream() {
		return bos;
	}
	
	private void streamMaker(String fnPrefix) {
		final String logfn = getLogFn(fnPrefix); 
		Log.info(TAG, "logfn:" + logfn);
		File logFile = new File(logfn);
		try {
			if(bos == null) {
				fos = new FileOutputStream(logFile);
				bos = new BufferedOutputStream(fos);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String getLogFn(String fnPrefix) {
		return dir + "/" + fnPrefix + TimeTools.getDate3() + "_" + getSequence();
	}

	/**
	 * Ensure the directory that use for save logs file exist.
	 * */
	private void checkLogsDir() {
		File logsDir = new File(dir);
		if(logsDir.exists()) {
			if(logsDir.isDirectory()) {
				// Do nothing.
			}else {
				// recreate.
				logsDir.renameTo(new File(dir + "_" + RandomTool.randomPercentage()));
				createLogsDir(dir);
			}
		}else {
			createLogsDir(dir);
		}
	}

	private void createLogsDir(String dir) {
		if(!new File(dir).mkdirs()) {
			throw new RuntimeException("Logs output directory create failed...");
		}
	}

	protected String getSequence() {
		StringBuilder sb = new StringBuilder();
		if(sequence <= 0) {
			sequence = 0;
			sb.append("00000");
		}else if(sequence > 99999) {
			sequence = 0;
			sb.append("00000");
		}else {
			if(sequence < 10) {
				sb.append("0000");
			}else if(sequence < 100) {
				sb.append("000");
			}else if(sequence < 1000) {
				sb.append("00");
			}else if(sequence < 10000) {
				sb.append("0");
			}
			
			sb.append(sequence);
		} // It works very well... 2019-02-06 20:14.
		
		sequence++;
		return sb.toString();
	}
	
}
