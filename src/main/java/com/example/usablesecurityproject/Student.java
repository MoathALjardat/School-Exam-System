package com.example.usablesecurityproject;

public class Student {
	int id;
	String password;
	String name;
	int phoneNumber;
	String email;
	int budget;

	public Student() {
		super();
	}
	
	public Student(String password, String name, int phoneNumber, String email, int budget) {
		super();
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.budget = budget;
	}

	public Student(int id, String name, int phoneNumber, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Student(int id, String password, String name, int phoneNumber, String email, int budget) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.budget = budget;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

}
