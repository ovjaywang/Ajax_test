package com.wjy.entity;

import java.util.List;

public class Country_CityList {
	Country ct;
	List<City> cities;
	public Country getCt() {
		return ct;
	}
	public void setCt(Country ct) {
		this.ct = ct;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public void insertCity(City ctt){
		this.cities.add(ctt);
	}
	@Override
	public String toString() {
		return "[Country " + ct + ", City " + cities + "]";
	}
	public Country_CityList(Country ct, List<City> cities) {
		super();
		this.ct = ct;
		this.cities = cities;
	}

}
