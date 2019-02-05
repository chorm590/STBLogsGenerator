package com.chorm.operator;

import java.util.List;

import com.chorm.factory.STBFactory;
import com.chorm.physical.device.STB;
import com.chorm.physical.person.Family;

/**
 * 运营商抽象类。
 * 运营商负责向工厂订制机顶盒以及售卖机顶盒。
 * */
public abstract class Operator {

	
	protected int stbSold;
	
	protected List<STB> stbs; //用List来管理机顶盒不太合理。 或许可以改成以机顶盒序列号为键，机顶盒对象为值的Map。
	
	protected Operator() {
		buySTB();
	}

	protected abstract int buySTBAmount();
	
	protected abstract STBFactory getSTBFactory();
	
	public STB applySTB(Family mFamily) {
		// pick a STB from list.
		STB stb = stbs.get(stbSold++);
		return stb;
	}
	
	/**
	 * 向工厂购买机顶盒。
	 * */
	private void buySTB() {
		stbs = getSTBFactory().createSTB(buySTBAmount());
		stbSold = 0;
	}
}
