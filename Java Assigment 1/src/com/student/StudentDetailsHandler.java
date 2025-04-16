package com.student;

import java.util.Arrays;

public class StudentDetailsHandler implements StudentInformation{

	@Override
	public Student[] getStudentDetails(String[][] studentsDetails) throws NumberFormatException {
		Student[] students = new Student[studentsDetails.length];
		int validStudentsCount = 0;
		for(int i = 0; i < studentsDetails.length; i++) {
			try {
			String studentName = studentsDetails[i][0];
			int studentRollNumber = Integer.parseInt(studentsDetails[i][1]);
			int studentAge = Integer.parseInt(studentsDetails[i][2]);
			students[validStudentsCount] = new Student(studentName, studentRollNumber, studentAge);
			validStudentsCount++;
			}catch(NumberFormatException exception) {
				System.out.println("Error parsing data for student " + (i+1) + ". Skipping this student details");
			}
		}
		return validStudentsCount < studentsDetails.length ? Arrays.copyOf(students, validStudentsCount): students;
	}

	@Override
	public void displayStudentDetails(Student[] students) {
		System.out.println("Student Details:\n");
		System.out.println("Student Name\t\tStudent Roll Number\t\tStudent Age");
		for(Student student : students) {
			System.out.println(student.getStudentName() + "\t\t" + student.getStudentRollNumber() + "\t\t\t\t" + 
		student.getStudentAge());
		}		
	}

}
