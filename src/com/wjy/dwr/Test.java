package com.wjy.dwr;

public class Test {
	public String hello(){
		return "Test类的hello方法Hello World！";
	}
	public Student getStudent(){
		Student s = new Student();
		s.setName("小白");
		s.setAge(10);
		return s;
	}
}
