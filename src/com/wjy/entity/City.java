package com.wjy.entity;

import java.util.List;

public class City {
	private String cityName ;
	private int cityId;
	private int countryId;
	private List<Resort> rs;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public List<Resort> getRs() {
		return rs;
	}
	public void setRs(List<Resort> rs) {
		this.rs = rs;
	}
	public void insertRs(Resort rss){
		this.rs.add(rss);
	}
	@Override
	public String toString() {
		return "[cityName :" + cityName + ", cityId :" + cityId
				+ ", countryId :" + countryId + ", rs :" + rs + "]";
	}
	public City(String cityName, int cityId, int countryId, List<Resort> rs) {
		super();
		this.cityName = cityName;
		this.cityId = cityId;
		this.countryId = countryId;
		this.rs = rs;
	}

	
}
