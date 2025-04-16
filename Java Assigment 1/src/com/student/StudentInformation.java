package com.student;

public interface StudentInformation {
	
	Student[] getStudentDetails(String[][] studentsDetails) throws NumberFormatException;
	void displayStudentDetails(Student[] students);

}
