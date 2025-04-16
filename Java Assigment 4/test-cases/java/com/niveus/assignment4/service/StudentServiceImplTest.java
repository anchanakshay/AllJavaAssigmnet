package com.niveus.assignment4.service;

import com.niveus.assignment4.entity.Student;
import com.niveus.assignment4.exception.DataNotFoundException;
import com.niveus.assignment4.model.ResponseBody;
import com.niveus.assignment4.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks StudentServiceImpl studentServiceImpl;

    @Mock
    StudentRepository studentRepository;

    List<Student> studentList = new ArrayList<>();


    private Student testStudent;
    private ResponseBody testResponseBody;
    private List<ResponseBody> testResponseBodyList;

    @BeforeEach
    public void setUp(){
        studentList.add(new Student(1101,"Akshay", 31, "Anchan"));
        studentList.add(new Student(1102,"Akshay", 24, "H"));
        testStudent = new Student();
        testResponseBody = new ResponseBody();
        testResponseBodyList = new ArrayList<>();
    }

    @Test
     void testGetAllStudentsDetails(){
        when(studentRepository.findAll()).thenReturn(studentList);
        //when
        List<Student> result= studentServiceImpl.getAllStudentDetails();
        //then
        assertEquals(studentList,result);
    }
    @Test
     void testGetStudentDetailsSuccess() throws DataNotFoundException {
        // given
        int studentRollNumber = 12;
        Student mockStudent = new Student();
        mockStudent.setStudentRollNumber(studentRollNumber);
        mockStudent.setStudentName("Uma");
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.of(mockStudent));

        // when
        ResponseEntity<Student> responseEntity = studentServiceImpl.getStudentDetails(studentRollNumber);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockStudent, responseEntity.getBody());
    }

    @Test
     void testGetStudentDetailsStudentNotFound() {
        // given
        int studentRollNumber = 999999;
        //when
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.empty());
        //then
        try {
            studentServiceImpl.getStudentDetails(studentRollNumber);
        } catch (DataNotFoundException e) {
            assertEquals("Student not found for this studentRollNumber :: 999999", e.getMessage());
        }
    }

    @Test
     void testAddStudentDetailsSuccess() {
        // given
        when(studentRepository.save(testStudent)).thenReturn(testStudent);

        // when
        ResponseEntity<List<ResponseBody>> responseEntity = studentServiceImpl.addStudentDetails(testStudent, testResponseBodyList, testResponseBody);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
        ResponseBody responseBody = responseEntity.getBody().get(0);
        assertEquals("200", responseBody.getStatusCode());
        assertEquals("Record Inserted", responseBody.getStatusMessage());
        verify(studentRepository, times(1)).save(testStudent);
    }

    @Test
     void testUpdateStudentDetailsSuccess() throws DataNotFoundException {
        // given
        int studentRollNumber = 123;
        Student studentDetails = new Student(123, "John", 25, "123 Street, City");
        List<ResponseBody> responseBody = new ArrayList<>();
        ResponseBody responseStatus = new ResponseBody();
        Student existingStudent = new Student(123, "PreviousName", 20, "456 Street, City");

        // when
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(studentDetails);
        ResponseEntity<List<ResponseBody>> responseEntity = studentServiceImpl.updateStudentDetails(studentRollNumber, studentDetails, responseBody, responseStatus);

        // then
        verify(studentRepository, times(1)).findById(studentRollNumber);
        verify(studentRepository, times(1)).save(any(Student.class));
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals("200", responseEntity.getBody().get(0).getStatusCode());
        assertEquals("UPDATED", responseEntity.getBody().get(0).getStatusMessage());
    }

    @Test
     void testUpdateStudentDetailsThrowsDataNotFoundException() {
        // given
        int studentRollNumber = 123;
        Student studentDetails = new Student(123, "John", 25, "123 Street, City");
        List<ResponseBody> responseBody = new ArrayList<>();
        ResponseBody responseStatus = new ResponseBody();

        // when
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.empty());

        try {
            studentServiceImpl.updateStudentDetails(studentRollNumber, studentDetails, responseBody, responseStatus);
        } catch (DataNotFoundException e) {
            assertEquals("Student not found for this studentRollNumber :: 123", e.getMessage());
        }

        // then
        verify(studentRepository, times(1)).findById(studentRollNumber);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
     void testDeleteStudentDetailsSuccess() throws DataNotFoundException {
        //given
        int studentRollNumber = 123;
        Student student = new Student(1101,"Akshay", 31, "Anchan");
        //when
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.of(student));

        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("DELETED", Boolean.TRUE);

        Map<String, Boolean> actualResponse = studentServiceImpl.deleteStudentDetails(studentRollNumber);
        //then
        assertEquals(expectedResponse, actualResponse);
        verify(studentRepository, times(1)).findById(studentRollNumber);
        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testDeleteStudentDetailsThrowsDataNotFoundException() {
        //given
        int studentRollNumber = 456;

        // when
        when(studentRepository.findById(studentRollNumber)).thenReturn(Optional.empty());

        // then
        assertThrows(DataNotFoundException.class, () -> studentServiceImpl.deleteStudentDetails(studentRollNumber));
    }


}
