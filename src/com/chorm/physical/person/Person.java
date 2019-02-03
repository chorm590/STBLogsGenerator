package com.chorm.physical.person;

import java.util.HashSet;
import java.util.Set;

import com.chorm.physical.device.STB;

public abstract class Person {
	
	private enum FavLabel {
		KEHUAN,
		ZONGYI,
		XIJU,
		XIQU,
		GUZHUANG,
		YANQING,
		KONGBU,
		DONGZUO;
	}
	
	private Set<FavLabel> favLabels = new HashSet<>();

	/**
	 * 每一个人都有他们的机顶盒子。
	 * 都可以操纵他们的机顶盒子。
	 * */
	public abstract STB getSTB();
	
	public abstract void setFavLabel();
	
	public Set<FavLabel> getFavLabel(){
		return favLabels;
	}
	
}
