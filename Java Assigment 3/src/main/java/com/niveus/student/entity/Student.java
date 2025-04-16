package com.niveus.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Student")
public class Student {
	@Id
	@NotNull
	@Column(name = "studentRollNumber")
	private int studentRollNumber;

	@Column(name = "studentName")
	private String studentName;

	@Column(name = "studentAge")
	private int studentAge;
	

	@Column(name = "studentAddress")
	private String studentAddress;


	public int getStudentRollNumber() {
		return studentRollNumber;
	}


	public void setStudentRollNumber(int studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public int getStudentAge() {
		return studentAge;
	}


	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}


	public String getStudentAddress() {
		return studentAddress;
	}


	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}


	@Override
	public String toString() {
		return "Student [studentRollNumber=" + studentRollNumber + ", studentName=" + studentName + ", studentAge="
				+ studentAge + ", studentAddress=" + studentAddress + "]";
	}

}
