package com.chorm.random;

/**
 * 这个工程的核心就是生成随机数。这个工程中含有很多种随机数生成器。
 * 这个接口类定义一个随机数生成器的抽象接口。
 * 目前已知的随机数生成器有如下几种：
 * 	1、播放器事件随机生成器。
 * 	2、机顶盒MAC和序列号随机数生成器。
 * 	3、厂家编码随机数生成器。
 * 	4、地区编码随机数生成器。
 * 
 * */
public interface Random<T> {
	
	java.util.Random mRandom = new java.util.Random();
	
	/**
	 * 生成一组随机数。
	 * 这个随机数一定是String类型的。
	 * */
	T generate();
}
