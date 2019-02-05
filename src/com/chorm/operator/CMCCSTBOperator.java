package com.chorm.operator;

import java.util.HashSet;
import java.util.Set;

import com.chorm.factory.STBFactory;
import com.chorm.factory.Unionman;
import com.chorm.physical.device.STB;
import com.chorm.physical.person.Family;
import com.chorm.utils.Controls;

/**
 * chorm on 2019-02-05 00:24. Happy spring festival.
 * */
public class CMCCSTBOperator extends Operator {
	
	private static Operator mOperator;
	
	private Set<Integer> familyInfo;
	
	private CMCCSTBOperator() {
		familyInfo = new HashSet<>();
	}
	
	public static Operator getInstance() {
		if(mOperator == null) {
			synchronized(CMCCSTBOperator.class) {
				if(mOperator == null) {
					mOperator = new CMCCSTBOperator();
				}
			}
		}
		
		return mOperator;
	}

	@Override
	protected int buySTBAmount() {
		return Controls.HB_CMCC_STB_AMOUNT;
	}

	@Override
	protected STBFactory getSTBFactory() {
		return new Unionman(); //hubei cmcc only recognize unionman...
	}

	@Override
	public STB applySTB(Family mFamily) {
		STB stb = super.applySTB(mFamily);
		
		// Register family information.
		familyInfo.add(mFamily.hashCode()); // Meaningless, actually.
		
		return stb;
	}
	
	

}
