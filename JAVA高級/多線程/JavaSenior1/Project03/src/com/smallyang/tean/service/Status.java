package com.smallyang.tean.service;

/**
 * 
* <p>Title: Status</p>  
	員工的狀態
* <p>Description: </p> 
 * @author USER
 * 2023年7月18日
 */
//public class Status {
//	private final String NAME;
//	private Status(String name) {
//		this.NAME = name;
//	}
//
//	public static final Status FREE = new Status("FREE");
//	public static final Status BUSY = new Status("BUSY");
//	public static final Status VOCATION = new Status("VOCATION");
//	public String getNAME() {
//		return NAME;
//	}
//
//	@Override
//		public String toString() {
//			// TODO Auto-generated method stub
//			return NAME;
//		}
//}

public enum Status {
	FREE,
	BUSY,
	VOCATION;
}