package com.example.usablesecurityproject;

public class Exam {
	int id;
	String type;
	String subject;
	int teacherId;
	
	public Exam() {
		super();
	}

	public Exam(int id, String type, String subject, int teacherId) {
		super();
		this.id = id;
		this.type = type;
		this.subject = subject;
		this.teacherId = teacherId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", type=" + type + ", subject=" + subject + ", teacherId=" + teacherId + "]";
	}
	
}
