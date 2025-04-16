package com.niveus.assignment4.service;

import java.util.List;
import java.util.Map;

import com.niveus.assignment4.entity.Student;
import com.niveus.assignment4.exception.DataNotFoundException;
import com.niveus.assignment4.model.ResponseBody;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;

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
