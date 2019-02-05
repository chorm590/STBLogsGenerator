package junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.chorm.factory.Unionman;
import com.chorm.operator.ProgramsOperator;
import com.chorm.others.ProgramBeans;
import com.chorm.physical.player.M301HPlayer;
import com.chorm.physical.player.Player;
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
		for(int i  = 0;i<100;i++)
			System.out.println(Math.sqrt(3)*rd.nextGaussian()+10);
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
	
	@Test
	public void playerTest() {
//		Set<ProgramBeans> programs = ProgramsOperator.getPrograms();
//		Player mPlayer = new M301HPlayer(null); //传null一定要保证不要报空指针异常错误。
//		ProgramBeans pb = programs.iterator().next();
//		Log.info(TAG, pb.getName() + ":" + pb.getUrl() + "," + pb.getDuration());
//		mPlayer.prepare(pb);
//		mPlayer.start(pb, 0);
		
	}
	
	@Test
	public void serialTest() {
//		new Unionman().createSerial(null);
//		new Unionman().createMac(null);
	}
}
