package com.student;

public class Student {
	
	private String studentName;
	private int studentRollNumber;
	private int studentAge;
	
	public Student(String studentName, int studentRollNumber, int studentAge) {
		super();
		this.studentName = studentName;
		this.studentRollNumber = studentRollNumber;
		this.studentAge = studentAge;
	}

	public String getStudentName() {
		return studentName;
	}

	public int getStudentRollNumber() {
		return studentRollNumber;
	}

	public int getStudentAge() {
		return studentAge;
	}
}
