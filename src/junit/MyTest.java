package junit;

import org.junit.Test;

import com.chorm.random.PlayerEventRandomGenerator;
import com.chorm.random.Random;
import com.chorm.utils.Constants;
import com.chorm.utils.Log;
import com.chorm.utils.NumericTools;

public class MyTest {
	
	private static final String TAG = "MyTest";

	@Test
	public void numericTool() {
		int n = Constants.DetectorProvince.GANSU;
		n = Constants.DetectorProvince.ZHEJIANG    ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.GUIZHOU     ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.SHANXI      ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.HENAN       ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.QINGHAI     ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.JILING      ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.NEIMENGGU   ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.NINGXIA     ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.HUNAN       ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.GUANGXI     ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.GANSU       ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.HUBEI       ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
		n = Constants.DetectorProvince.HAINAN      ;
		Log.info(TAG, n +" -> " + NumericTools.int2String(n));
	}
	
	@Test
	public void randomTest() {
		Random rd = new PlayerEventRandomGenerator();
		rd.generate();
		Log.info(TAG, "====");
	}
}
