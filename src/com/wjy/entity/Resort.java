package com.wjy.entity;

public class Resort {
	private String ResortName;
	private int cityId;
	private int resortId;
	@Override
	public String toString() {
		return "Resort [ResortName=" + ResortName + ", cityId=" + cityId
				+ ", resortId=" + resortId + "]";
	}
	public Resort(String resortName, int cityId, int resortId) {
		super();
		ResortName = resortName;
		this.cityId = cityId;
		this.resortId = resortId;
	}
	public String getResortName() {
		return ResortName;
	}
	public void setResortName(String resortName) {
		ResortName = resortName;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getResortId() {
		return resortId;
	}
	public void setResortId(int resortId) {
		this.resortId = resortId;
	}
	
}
