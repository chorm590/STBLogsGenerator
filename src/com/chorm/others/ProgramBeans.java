package com.chorm.others;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramBeans {

	private String name;
	private String url;
	private int duration;
	private ProgramType pgtype;
	private boolean isRecommended;
	private VideoPixel pixel;
	private int currentPosition;
	
	/**
	 * 首页推荐的上线时间。
	 * */
	private long upLineTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ProgramType getPgtype() {
		return pgtype;
	}

	public void setPgtype(ProgramType pgtype) {
		this.pgtype = pgtype;
	}

	public boolean isRecommended() {
		return isRecommended;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public VideoPixel getPixel() {
		return pixel;
	}

	public void setPixel(VideoPixel pixel) {
		this.pixel = pixel;
	}

	public long getUpLineTime() {
		return upLineTime;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentDuration) {
		this.currentPosition = currentDuration;
	}

	/**
	 * 将抽象的毫秒时间值转换成宜读的字符串形式日期。
	 * */
	public String getUpLineTimeReadable() {
		Date date = new Date(getUpLineTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public void setUpLineTime(long upLineTime) {
		if(upLineTime < 0)
			throw new IllegalArgumentException("The upline time can't be negative.");
		this.upLineTime = upLineTime;
	}
	
	@Override
	public String toString() {
		return name + "\t" + url + "\t" + duration + "\t" + pgtype
				+ "\t" + pixel + "\t" + isRecommended + "\t" + upLineTime;
	}
	
	// HashSet 去重。
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return name.equals(((ProgramBeans)obj).name);
	}

}
