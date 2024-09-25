package com.smallyang.tean.domain;

public class Designer extends Programmer{
	private double bonus;

	public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}

	public Designer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return getDetails() + "\t設計師\t" + getStatus() + "\t" + bonus +"\t\t" + getEquipment().getDescription();
	}
	
	public String getDetailsForTeam() {
		return getTeamBaseDetails() + "/" + getId() + "\t" + getName() + "\t" + +getAge() + "\t" + getSalary() + "\t" + "\t設計師\t" + getBonus();
	}
}
