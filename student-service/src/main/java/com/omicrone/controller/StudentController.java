package com.omicrone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omicrone.request.CreateStudentRequest;
import com.omicrone.response.StudentResponse;
import com.omicrone.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		System.out.println("FROM STUDENT SERVICE");

		return studentService.createStudent(createStudentRequest);
	}

	@GetMapping("/getAllStudents")
	public List<StudentResponse> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}
	
}
