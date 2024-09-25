package com.smallyang.tean.domain;

public class Architect extends Designer{
	private int stock;

	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}

	public Architect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return getDetails() + "\t設計師\t" + getStatus() + "\t" + getBonus() + "\t" + stock +"\t" + getEquipment().getDescription();
	}
	
	public String getDetailsForTeam() {
		return getMemberId() + "/" + getId() + "\t" + getName() + "\t" + +getAge() + "\t" + getSalary() + "\t" + "\t架構師\t" + getBonus() + "\t" + getStock();
	}
}
