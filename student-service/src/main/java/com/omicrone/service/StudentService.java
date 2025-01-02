package com.omicrone.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.omicrone.entity.Student;
import com.omicrone.exceptions.StudentNotFoundException;
import com.omicrone.feignclients.FeignClient;
import com.omicrone.repository.StudentRepository;
import com.omicrone.request.CreateAddressRequest;
import com.omicrone.request.CreateStudentRequest;
import com.omicrone.response.AddressResponse;
import com.omicrone.response.StudentResponse;

@Service
public class StudentService {

	@Autowired
    StudentRepository studentRepository;

	@Autowired
	FeignClient feignClient;

	@CrossOrigin(origins = "http://localhost:8077")
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		// Create a new Student entity
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(createStudentRequest.getAddressId());

		// Save the student entity to the database
		Student savedStudent = studentRepository.save(student);

		// Create a new StudentResponse object
		StudentResponse studentResponse = new StudentResponse(savedStudent);

		// Fetch the address using Feign Client
		AddressResponse addressResponse = feignClient.getById(createStudentRequest.getAddressId());

		// Set the address details to the student response
		studentResponse.setAddressResponse(addressResponse);

		return studentResponse;
	}




	public StudentResponse getById(long id) {
		Optional<Student> studentOpt = studentRepository.findById(id);
		if (studentOpt.isPresent()) {
			Student student = studentOpt.get();
			StudentResponse studentResponse = new StudentResponse(student);
			studentResponse.setAddressResponse(feignClient.getById(student.getAddressId()));
			return studentResponse;
		} else {
			throw new StudentNotFoundException("Student not found with ID: " + id);
		}
	}

	public List<StudentResponse> getAllStudents() {
		// Retrieve all students from the repository
		List<Student> students = studentRepository.findAll();

		// Convert the list of students into a list of StudentResponse objects
		List<StudentResponse> studentResponses = students.stream()
				.map(student -> {
					// Create the StudentResponse object
					StudentResponse studentResponse = new StudentResponse(student);

					// Fetch the address using Feign Client
					AddressResponse addressResponse = feignClient.getById(student.getAddressId());

					// Set the address details to the student response
					studentResponse.setAddressResponse(addressResponse);

					return studentResponse;
				})
				.collect(Collectors.toList());

		// Return the list of StudentResponse objects
		return studentResponses;
	}

}
