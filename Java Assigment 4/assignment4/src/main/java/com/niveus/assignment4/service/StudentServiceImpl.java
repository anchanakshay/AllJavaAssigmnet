package com.niveus.assignment4.service;

import com.niveus.assignment4.entity.Student;
import com.niveus.assignment4.exception.DataNotFoundException;
import com.niveus.assignment4.model.ResponseBody;
import com.niveus.assignment4.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StudentServiceImpl implements StudentService
{

	private final StudentRepository studentRepository;
	private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found for this studentRollNumber :: ";


	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	@Override
	public List<Student> getAllStudentDetails() {
		return studentRepository.findAll();
	}

	@Override
	public ResponseEntity<Student> getStudentDetails(int studentRollNumber) throws DataNotFoundException {
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException(
						STUDENT_NOT_FOUND_MESSAGE + studentRollNumber));
		return ResponseEntity.ok().body(student);
	}


	@Override
	public ResponseEntity<List<ResponseBody>> addStudentDetails(
			@Valid Student student, List<ResponseBody> responseBody, ResponseBody responseStatus) {
		List<ResponseBody> newResponseBody = new ArrayList<>();
		ResponseBody newResponseStatus = new ResponseBody();
		studentRepository.save(student);
		newResponseStatus.setStatusCode("200");
		newResponseStatus.setStatusMessage("Record Inserted");
		newResponseBody.add(newResponseStatus);
		return ResponseEntity.ok(newResponseBody);
	}

	@Override
	public ResponseEntity<List<ResponseBody>> updateStudentDetails(int studentRollNumber, @Valid Student studentDetails,
																   List<ResponseBody> responseBody, ResponseBody responseStatus) throws DataNotFoundException {
		List<ResponseBody> updatedResponseBody = new ArrayList<>(responseBody);
		ResponseBody updatedResponseStatus = new ResponseBody();
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException(STUDENT_NOT_FOUND_MESSAGE + studentRollNumber));
		student.setStudentRollNumber(studentDetails.getStudentRollNumber());
		student.setStudentAge(studentDetails.getStudentAge());
		student.setStudentName(studentDetails.getStudentName());
		student.setStudentAddress(studentDetails.getStudentAddress());
		studentRepository.save(student);
		updatedResponseStatus.setStatusCode("200");
		updatedResponseStatus.setStatusMessage("UPDATED");
		updatedResponseBody.add(updatedResponseStatus);
		return ResponseEntity.ok(updatedResponseBody);
	}

	@Override
	public Map<String, Boolean> deleteStudentDetails(int studentRollNumber) throws DataNotFoundException {
		Student student = studentRepository.findById(studentRollNumber)
				.orElseThrow(() -> new DataNotFoundException(STUDENT_NOT_FOUND_MESSAGE + studentRollNumber));
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("DELETED", Boolean.TRUE);
		return response;
	}
	
}
