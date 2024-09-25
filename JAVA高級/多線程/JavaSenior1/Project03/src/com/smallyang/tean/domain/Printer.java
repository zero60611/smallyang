package com.smallyang.tean.domain;

public class Printer implements Equipment{
	String name;
	String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Printer(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public Printer() {
		super();
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return name + "("+ type+ ")";
	}
	
}
