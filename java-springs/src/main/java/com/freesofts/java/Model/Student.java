package com.freesofts.java.Model;

public class Student {
	private int id;
	private String name;
	private String std;
	
	public Student(int id, String name,String std) {
		this.id = id;
		this.name = name;
		this.std = std;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStd() {
		return std;
	}
}