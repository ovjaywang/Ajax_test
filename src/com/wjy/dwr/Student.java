package com.wjy.dwr;

public class Student {
	private String Name;
	private int Age;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public Student(String name, int age) {
		super();
		Name = name;
		Age = age;
	}
	public Student() {
		super();
	}
	
}
