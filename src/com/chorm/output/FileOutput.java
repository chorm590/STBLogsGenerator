package com.chorm.output;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
	
	/**
	 * 当换日，在滚动目录和流时将该标志位置为true，此时禁止向文件写数据。
	 * */
	protected static boolean isRolling;
	
	protected static final byte NEW_LINE[] = {'\r', '\n'};
	
	protected static String dir;
	protected final String FN_PREFIX;
	protected final String FN_DIR_ROOT;
	protected final String FN_SUFFIX;
	protected String currentDate;
	
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	protected static StringBuilder sba;
	
	protected FileOutput(String fnPrefix, CompressType compressType, String storagePath) {
		FN_PREFIX = fnPrefix;
		FN_DIR_ROOT = storagePath;
		FN_SUFFIX = getFnSuffix(compressType);
		sba = new StringBuilder();
		currentDate = TimeTools.getDate3();
		ensureLogsDir();
		makeFileStream();
		//Logs roll up timer task.
		new Timer().schedule(new LogsRollUpTimerTask(), 10000, 1000);
	}
	
	private String getFnSuffix(CompressType compressType) {
		switch(compressType) {
			case NO_COMPRESS:
				return ".log";
			default:
				return ".log";
		}
	}

	public void write(String line) {
		if(isRolling) {
			sba.append(line);
			sba.append("\r\n");
			return;
		}else {
			if(sba.length() > 0) {
				writeToFile(sba.toString().getBytes());
				sba.delete(0, sba.length());
			}
		}
		
		writeToFile(line.getBytes());		
	}
	
	protected void writeToFile(byte dat[]) {
		if(fileSize + dat.length >= FILE_SIZE_MAXIMUM) {
			Log.info(TAG, "rolling a new log file. Current sequence:" + sequence);
			rollLogsFileSizeUp();
		}
		
		try {
			bos.write(dat);
			bos.write(NEW_LINE);
			bos.flush();
			fos.flush();
			fileSize += dat.length;
			fileSize += 2; //NEW_LINE 2 character.
//			Log.info(TAG, "fileSize:" + fileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void flushAndCloseStream() {
		try {
			bos.flush();
			fos.flush();
			
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
	
	private void makeFileStream() {
		final String logfn = getLogFn(); 
		Log.info(TAG, "logfn:" + logfn);
		File logFile = new File(logfn);
		try {
			if(bos == null) {
				fos = new FileOutputStream(logFile);
				bos = new BufferedOutputStream(fos);
			}
			fileSize = 0; //Must reset it.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String getLogFn() {
		return dir + "/" + FN_PREFIX + TimeTools.getDate3() + "_" + getSequence() + FN_SUFFIX;
	}

	/**
	 * Ensure the directory that use for save logs file exist.
	 * Don't need to think about the 'date' matter.
	 * */
	private void ensureLogsDir() {
		// Step 1, ensure path.
		dir = FN_DIR_ROOT + "/" + TimeTools.getDate3();
		// Step 2, check existence.
		File logsDir = new File(dir);
		if(logsDir.exists()) {
			if(logsDir.isDirectory()) {
				// You could think this won't be happen.
			}else {
				// recreate.
				logsDir.renameTo(new File(dir + "_" + RandomTool.randomPercentage()));
				createLogsDir();
			}
		}else {
			createLogsDir();
		}
		
	}

	/**
	 * Make logs persistent directory.
	 * */
	private void createLogsDir() {
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
	
	private void rollDateUp() {
		Log.info(TAG, "rolling up... " + currentDate + " --> " + TimeTools.getDate3());
		// Step 1, set the symbol.
		isRolling = true;
		// Step 2, ensure directory.
		ensureLogsDir();
		// Step 3, make file stream.
		sequence = -1; //Reset sequence number.
		flushAndCloseStream();
		makeFileStream();
		currentDate = TimeTools.getDate3();
		// Step 4, reset the symbol.
		isRolling = false;
		Log.info(TAG, "roll up finished!");
	}
	
	private void rollLogsFileSizeUp() {
		isRolling = true;
		flushAndCloseStream();

//		ensureLogsDir();
		makeFileStream();
		
		// Step 3, 
		isRolling = false;
	}
	
	/**
	 * Grows up with time flow.
	 * chorm on 2019-02-07 12:29.
	 * */
	private class LogsRollUpTimerTask extends TimerTask {

		@Override
		public void run() {
			if(FileOutput.this.currentDate.equals(TimeTools.getDate3())) {
				return;
			}
			
			// The day grows up!
			FileOutput.this.rollDateUp();
		}
		
	}

}
