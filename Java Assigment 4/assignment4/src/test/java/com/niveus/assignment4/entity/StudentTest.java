package com.niveus.assignment4.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
     void testConstructorsAndGetters(){
        Student student = new Student(123,"Akshay",27,"Udupi");

        Assertions.assertEquals(1203,student.getStudentRollNumber());
        Assertions.assertEquals("Akshay",student.getStudentName());
        Assertions.assertEquals(47,student.getStudentAge());
        Assertions.assertEquals("Udupi",student.getStudentAddress());
    }

    @Test
     void testSettersAndToString(){
        Student student = new Student();
        student.setStudentRollNumber(1201);
        student.setStudentName("Thilak");
        student.setStudentAge(28);
        student.setStudentAddress("Mangalore");
        String expectedString="Student [studentRollNumber=1201, studentName=Thilak, studentAge=28, studentAddress=Mangalore]";

        Assertions.assertEquals(1201,student.getStudentRollNumber());
        Assertions.assertEquals("Thilak",student.getStudentName());
        Assertions.assertEquals(28,student.getStudentAge());
        Assertions.assertEquals("Mangalore",student.getStudentAddress());
        Assertions.assertEquals(expectedString,student.toString());
    }

}
