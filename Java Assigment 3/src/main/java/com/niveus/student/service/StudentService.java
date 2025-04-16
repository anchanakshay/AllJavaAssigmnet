package com.niveus.student.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.niveus.student.entity.Student;
import com.niveus.student.exception.DataNotFoundException;
import com.niveus.student.model.ResponseBody;

import jakarta.validation.Valid;

public interface StudentService {
	
	List<Student> getAllStudentDetails();

	ResponseEntity<Student> getStudentDetails(int studentRollNumber) throws DataNotFoundException;

	ResponseEntity<List<ResponseBody>> addStudentDetails(
			@Valid Student student, List<ResponseBody> responseBody, ResponseBody responseStatus);

	ResponseEntity<List<ResponseBody>> updateStudentDetails(
			int studentRollNumber, @Valid Student studentDetails, List<ResponseBody> responseBody,
			ResponseBody responseStatus) throws DataNotFoundException;

	Map<String, Boolean> deleteStudentDetails(int studentRollNumber) throws DataNotFoundException;

}
