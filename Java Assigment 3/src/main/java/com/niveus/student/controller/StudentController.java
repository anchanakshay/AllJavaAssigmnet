package com.niveus.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niveus.student.entity.Student;
import com.niveus.student.exception.DataNotFoundException;
import com.niveus.student.model.ResponseBody;
import com.niveus.student.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired private StudentService studentService;
	
	List<ResponseBody> responseBody = null;
	ResponseBody responseStatus = null;
	
	@GetMapping("/get")
	public List<Student> getAllStudents() {
		return studentService.getAllStudentDetails();
	}


	@GetMapping("/get/{studentRollNumber}")
	public ResponseEntity<Student> getStudentByRollNumber(
			@PathVariable(value = "studentRollNumber") int studentRollNumber) throws DataNotFoundException{
		return studentService.getStudentDetails(studentRollNumber);
	}

	@PostMapping("/add")
	public ResponseEntity<List<ResponseBody>> createStudent(@Valid @RequestBody Student student) {
		return studentService.addStudentDetails(student,responseBody,responseStatus);
	}

	@PutMapping("/update/{studentRollNumber}")
	public ResponseEntity<List<ResponseBody>> updateStudent(
			@PathVariable(value = "studentRollNumber") int studentRollNumber,
			@Valid @RequestBody Student studentDetails) throws DataNotFoundException {
		return studentService.updateStudentDetails(studentRollNumber, studentDetails,responseBody,responseStatus);

	}

	@DeleteMapping("/delete/{studentRollNumber}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "studentRollNumber") int studentRollNumber)
			throws DataNotFoundException {
		return studentService.deleteStudentDetails(studentRollNumber);
	}

}
