package com.example.usablesecurityproject;

public class Fee {
	int id;
	int amountPaid;
	int teacherId;
	int studentId;
	int managerSSN;

	public Fee() {
		super();
	}

	public Fee(int id, int amountPaid, int studentId, int managerSSN) {
		super();
		this.id = id;
		this.amountPaid = amountPaid;
		this.studentId = studentId;
		this.managerSSN = managerSSN;
	}

	public Fee(int id, int amountPaid, int teacherId, int studentId, int managerSSN) {
		super();
		this.id = id;
		this.amountPaid = amountPaid;
		this.teacherId = teacherId;
		this.studentId = studentId;
		this.managerSSN = managerSSN;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getManagerSSN() {
		return managerSSN;
	}

	public void setManagerSSN(int managerSSN) {
		this.managerSSN = managerSSN;
	}

}
