package junit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.chorm.random.PlayerEventRandomGenerator;
import com.chorm.random.Random;
import com.chorm.utils.Log;
import com.chorm.utils.NumericTools;

public class MyTest {
	
	private static final String TAG = "MyTest";

	@Test
	public void numericTool() {
		
	}
	
	@Test
	public void randomTest() {
		Random rd = new PlayerEventRandomGenerator();
		rd.generate();
		Log.info(TAG, "====");
	}
	
	@Test
	public void time() {
		long t = System.currentTimeMillis();
		System.out.println(t);
		
		Date d = new Date(t);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String j = sdf.format(d);
		System.out.println(j);
		
	}
}
