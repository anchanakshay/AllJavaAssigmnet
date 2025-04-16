package com.niveus.assignment4.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
     void testConstructorsAndGetters(){
        Student student = new Student(1023,"Akshay",17,"Udupi");

        Assertions.assertEquals(1023,student.getStudentRollNumber());
        Assertions.assertEquals("Akshay",student.getStudentName());
        Assertions.assertEquals(17,student.getStudentAge());
        Assertions.assertEquals("Udupi",student.getStudentAddress());
    }

    @Test
     void testSettersAndToString(){
        Student student = new Student();
        student.setStudentRollNumber(1021);
        student.setStudentName("Thilak");
        student.setStudentAge(44);
        student.setStudentAddress("Udupi");
        String expectedString="Student [studentRollNumber=1021, studentName=Thilak, studentAge=44, studentAddress=Udupi]";

        Assertions.assertEquals(1021,student.getStudentRollNumber());
        Assertions.assertEquals("Thilak",student.getStudentName());
        Assertions.assertEquals(44,student.getStudentAge());
        Assertions.assertEquals("Udupi",student.getStudentAddress());
        Assertions.assertEquals(expectedString,student.toString());
    }

}
