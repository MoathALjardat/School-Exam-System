package com.example.usablesecurityproject;

public class Question {
	int id;
	int examId;
	int answer;
	String text;

	public Question() {
		super();
	}

	public Question(int id, int examId, int answer, String text) {
		super();
		this.id = id;
		this.examId = examId;
		this.answer = answer;
		this.text = text;
	}

	public Question(int id, String text, int answer, int examId) {
		super();
		this.id = id;
		this.text = text;
		this.answer = answer;
		this.examId = examId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", examId=" + examId + ", answer=" + answer + ", text=" + text + "]";
	}

}
