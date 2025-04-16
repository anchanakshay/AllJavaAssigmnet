package com.niveus.assignment4.controller;

import com.niveus.assignment4.entity.Student;
import com.niveus.assignment4.exception.DataNotFoundException;
import com.niveus.assignment4.model.ResponseBody;
import com.niveus.assignment4.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks StudentController studentController;
    @Mock
    StudentService studentService;

    @Test
    void getAllStudentsTest(){
        //given
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1101,"Akshay", 41, "Anchan"));
        studentList.add(new Student(1102,"Akshay", 57, "K"));
        when(studentService.getAllStudentDetails()).thenReturn(studentList);
        //when
        List<Student> result= studentController.getAllStudents();
        //then
        assertEquals(studentList,result);
    }

    @Test
     void testGetStudentByRollNumber() throws DataNotFoundException {
        // given
        Student mockStudent = new Student();
        mockStudent.setStudentRollNumber(123456);
        mockStudent.setStudentName("Abhishek");
        //when
        ResponseEntity<Student> mockResponseEntity = new ResponseEntity<>(mockStudent, HttpStatus.OK);
        when(studentService.getStudentDetails(123456)).thenReturn(mockResponseEntity);
        ResponseEntity<Student> response = studentController.getStudentByRollNumber(123456);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockStudent, response.getBody());
    }


    @Test
    void testCreateStudent(){
        //given
        Student student = new Student(1101,"Akshay", 21, "Anchan");
        ResponseEntity<List<ResponseBody>> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
        //when
        when(studentService.addStudentDetails(student,null,null)).thenReturn(expectedResponse);
        ResponseEntity<List<ResponseBody>> actualResponse = studentController.createStudent(student);
        //then
        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testUpdateStudent() throws DataNotFoundException {
        //given
        int studentRollNumber = 113;
        Student studentDetails = new Student(studentRollNumber,"Abhi", 26, "Poojary");
        ResponseEntity<List<ResponseBody>> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        //when
        when(studentService.updateStudentDetails(studentRollNumber,studentDetails,null,null)).thenReturn(expectedResponse);
        ResponseEntity<List<ResponseBody>> actualResponse = studentController.updateStudent(studentRollNumber,studentDetails);
        //then
        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testDeleteStudentSuccess() throws DataNotFoundException {
        // given
        when(studentService.deleteStudentDetails(anyInt())).thenReturn(Map.of("deleted", true));
        Map<String, Boolean> result = studentController.deleteStudent(123);

        // when
        verify(studentService).deleteStudentDetails(123);

        // then
        assertEquals(Map.of("deleted", true), result);
    }

    @Test
    void testDeleteStudentNotFound() throws DataNotFoundException {
        // given
        when(studentService.deleteStudentDetails(anyInt())).thenThrow(DataNotFoundException.class);

        // when
        assertThrows(DataNotFoundException.class, () -> studentController.deleteStudent(123));

        // then
        verify(studentService).deleteStudentDetails(123);
    }
}
