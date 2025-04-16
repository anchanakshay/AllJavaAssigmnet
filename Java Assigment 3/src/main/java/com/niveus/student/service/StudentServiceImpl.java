package com.niveus.student.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.niveus.student.entity.Student;
import com.niveus.student.exception.DataNotFoundException;
import com.niveus.student.model.ResponseBody;
import com.niveus.student.repository.StudentRepository;

import jakarta.validation.Valid;



@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	

	@Override
	public List<Student> getAllStudentDetails() {
		return studentRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Student> getStudentDetails(int studentRollNumber) throws DataNotFoundException {
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException(
						"Student not found for this studentRollNumber :: " + studentRollNumber));
		return ResponseEntity.ok().body(student);
	}

	@Override
	public ResponseEntity<List<ResponseBody>> addStudentDetails(
			@Valid Student student,List<ResponseBody> responseBody,ResponseBody responseStatus) {
		responseBody = new ArrayList<>();
		responseStatus = new ResponseBody();
		studentRepository.save(student);	
		responseStatus.setStatusCode("200");
		responseStatus.setStatusMessage("Record Inserted");
		responseBody.add(responseStatus);
		return ResponseEntity.ok(responseBody);
	}

	@Override
	public ResponseEntity<List<ResponseBody>> updateStudentDetails(int studentRollNumber, @Valid Student studentDetails,
			List<ResponseBody> responseBody, ResponseBody responseStatus) throws DataNotFoundException {
		responseBody = new ArrayList<>();
		responseStatus = new ResponseBody();
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException("Student not found for this studentRollNumber :: " + studentRollNumber));
		student.setStudentRollNumber(studentDetails.getStudentRollNumber());
		student.setStudentAge(studentDetails.getStudentAge());
		student.setStudentName(studentDetails.getStudentName());
		student.setStudentAddress(studentDetails.getStudentAddress());
		studentRepository.save(student);
		responseStatus.setStatusCode("200");
		responseStatus.setStatusMessage("UPDATED");
		responseBody.add(responseStatus);

		return ResponseEntity.ok(responseBody);
	}

	@Override
	public Map<String, Boolean> deleteStudentDetails(int studentRollNumber) throws DataNotFoundException {
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException("Student not found for this studentRollNumber :: " + studentRollNumber));
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("DELETED", Boolean.TRUE);
		return response;
	}

	
}
