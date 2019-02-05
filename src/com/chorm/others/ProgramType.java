package com.chorm.others;

public enum ProgramType {
		KEHUAN,
		ZONGYI,
		XIJU,
		XIQU,
		GUZHUANG,
		YANQING,
		KONGBU,
		DONGZUO;
	
	public static ProgramType getProgramType(int i) {
		switch(i) {
			case 0:
				return KEHUAN;
			case 1:
				return ProgramType.ZONGYI;
			case 2:
				return XIJU;
			case 3:
				return XIQU;
			case 4:
				return GUZHUANG;
			case 5:
				return ProgramType.YANQING;
			case 6:
				return ProgramType.KONGBU;
			case 7:
				return ProgramType.DONGZUO;
			default:
				return null;
		}
	}
}
