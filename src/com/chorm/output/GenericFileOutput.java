package com.chorm.output;

import com.chorm.others.CompressType;
import com.chorm.utils.Controls;

public class GenericFileOutput extends FileOutput {
	
	private static final String TAG = "GenericFileOutput";
	
	private static GenericFileOutput mInstance;

	private GenericFileOutput(String fnPrefix, CompressType compressType, String storagePath) {
		super(fnPrefix, compressType, storagePath);
	}
	
	public static GenericFileOutput getInstance() {
		if(mInstance == null) {
			synchronized (GenericFileOutput.class) {
				if(mInstance == null) {
					mInstance = new GenericFileOutput(Controls.LOGS_FILENAME_PREFIX,
							CompressType.NO_COMPRESS,
							Controls.LOGS_FILES_OUTPUT_DIRECTORY);
				}
			}
		}
		
		return mInstance;
	}

}
