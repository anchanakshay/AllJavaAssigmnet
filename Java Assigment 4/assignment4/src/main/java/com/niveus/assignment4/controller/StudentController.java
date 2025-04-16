package com.niveus.assignment4.controller;

import com.niveus.assignment4.entity.Student;
import com.niveus.assignment4.exception.DataNotFoundException;
import com.niveus.assignment4.model.ResponseBody;
import com.niveus.assignment4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

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
