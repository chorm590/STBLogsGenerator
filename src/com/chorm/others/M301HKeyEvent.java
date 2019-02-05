package com.chorm.others;

public enum M301HKeyEvent {

	KEYCODE_DOWN,
	KEYCODE_UP,
	KEYCODE_LEFT,  // 2  -- 3
	KEYCODE_RIGHT,
	KEYCODE_CENTER, //4  -- 5
	KEYCODE_POWER,
	KEYCODE_BACK;
	
	public static M301HKeyEvent parse(int keyvalue) {
		switch(keyvalue) {
			case 0:
				return KEYCODE_DOWN;
			case 1:
				return KEYCODE_UP;
			case 2:
				return KEYCODE_LEFT;
			case 3:
				return KEYCODE_RIGHT;
			case 4:
				return KEYCODE_CENTER;
			case 5:
				return KEYCODE_POWER;
			case 6:
				return KEYCODE_BACK;
		}
		
		return null;
	}
}
