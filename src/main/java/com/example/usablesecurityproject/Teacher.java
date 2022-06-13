package com.example.usablesecurityproject;

public class Teacher {
	int id;
	String name;
	int budget;
	String email;
	String password;

	public Teacher() {
		super();
	}

	public Teacher(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Teacher(String name, String email) {
		super();

		this.name = name;

		this.email = email;

	}

	public Teacher(int id, String name, String email) {
		super();
		this.id = id;

		this.name = name;

		this.email = email;

	}

	public Teacher(int id, String name, int budget, String email) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.email = email;

	}

	public Teacher(int id, String name, String email, int budget, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.budget = budget;
		this.password = password;
		
	}

	public Teacher(int id, String name, int budget, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", budget=" + budget + ", email=" + email + "]";
	}

}
