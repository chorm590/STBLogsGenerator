package junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.chorm.others.ProgramBeans;
import com.chorm.others.ProgramsOperator;
import com.chorm.random.PlayerEventRandomGenerator;
import com.chorm.random.ProgramsGenerator;
import com.chorm.utils.Log;
import com.chorm.utils.NumericTools;

public class MyTest {
	
	private static final String TAG = "MyTest";

	@Test
	public void numericTool() {
		
	}
	
	@Test
	public void randomTest() {
		Random rd = new Random();
		for(int i  = 0;i<10;i++)
			System.out.println(rd.nextInt(3));
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

	@Test
	public void programsGenTest() {
//		ProgramsGenerator pg = new ProgramsGenerator();
//		for(int i = 0; i < 30; i++) {
//			ProgramBeans pb = pg.generate();
//			System.out.println(pb);
//		}
		
		ProgramsOperator.getPrograms();
	}
	
//	@Test
//	public void programsGenTest() {
//		new ProgramsGenerator();
//	}
}
