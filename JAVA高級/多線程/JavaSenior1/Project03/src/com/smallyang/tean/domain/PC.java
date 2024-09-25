package com.smallyang.tean.domain;

public class PC implements Equipment{
	public PC() {
		super();
	}
	
	public PC(String model, String display) {
		this.model = model;
		this.display = display;
	}
	String model;// 機器型號
	String display;// 顯示器名稱
	
	@Override
	public String getDescription() {
		return model + "("+ display + ")";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	
}
