package com.niveus.assignment4.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	public Student() {

	}

	public Student(@NotNull int studentRollNumber, String studentName, int studentAge, String studentAddress) {
		super();
		this.studentRollNumber = studentRollNumber;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentAddress = studentAddress;
	}

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
