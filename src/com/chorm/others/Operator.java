package com.chorm.others;

/**
 * 运营商抽象类。
 * 运营商负责向工厂订制机顶盒以及售卖机顶盒。
 * */
public abstract class Operator {

	protected abstract int buySTBAmount();
	
	/**
	 * 向工厂购买机顶盒。
	 * */
	private void buySTB() {
		
	}
}
