package com.omicrone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

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

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		// Create a new Student entity
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());

		// Check if addressId exists in the request, if so, associate the student with the existing address
		if (createStudentRequest.getAddressId() != null) {
			AddressResponse existingAddress = feignClient.getById(createStudentRequest.getAddressId());
			if (existingAddress == null) {
				throw new RuntimeException("Address not found with ID: " + createStudentRequest.getAddressId());
			}
			student.setAddressId(createStudentRequest.getAddressId());
		} else {
			// If addressId doesn't exist, create a new address for the student
			CreateAddressRequest addressRequest = new CreateAddressRequest();
			addressRequest.setCity(createStudentRequest.getCity());
			addressRequest.setStreet(createStudentRequest.getStreet());

			AddressResponse addressResponse = feignClient.createAddress(addressRequest);

			student.setAddressId(addressResponse.getAddressId());
		}

		// Save student to the database
		student = studentRepository.save(student);

		// Prepare the response object
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(feignClient.getById(student.getAddressId()));

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
}
