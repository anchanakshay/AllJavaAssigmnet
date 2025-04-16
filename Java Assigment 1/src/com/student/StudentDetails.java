package com.student;

public class StudentDetails {
	
	public static void main(String[] args) {
		String[][] studentDetails = {
				{
					"Akshay", "11", "47"
				},
				{
					"Anchan", "12", "abc"
				},
				
		};
		StudentDetailsHandler studentDetailsHandler = new StudentDetailsHandler();
		try {
		Student[] students = studentDetailsHandler.getStudentDetails(studentDetails);
		studentDetailsHandler.displayStudentDetails(students);
		}catch(NumberFormatException exception) {
			System.out.println("NumberFormatException occured: " +exception.getMessage());
		}
	}
}

