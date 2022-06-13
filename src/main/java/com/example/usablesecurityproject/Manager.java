package com.example.usablesecurityproject;

public class Manager {
	int SSN;
	long phoneNumber;
	String name;
	int budget;
	String password;
	
	public Manager(int sSN, long phoneNumber, String name, int budget, String password) {
		super();
		SSN = sSN;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.budget = budget;
		this.password = password;
	}

	public Manager() {
		super();
	}

	public Manager(int sSN, long phoneNumber, String name, int budget) {
		super();
		SSN = sSN;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.budget = budget;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Manager [SSN=" + SSN + ", phoneNumber=" + phoneNumber + ", name=" + name + ", budget=" + budget
				+ ", password=" + password + "]";
	}
	
}
