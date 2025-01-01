package com.omicrone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;
import com.omicrone.service.AddressService;

@RestController
@CrossOrigin(origins = "http://frontend:8077", allowedHeaders = "*")
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	@Value("${server.port}")
	private String serverPort;
	@PostMapping("/create")
	public AddressResponse createAddress (@RequestBody CreateAddressRequest createAddressRequest) {
		System.out.println(serverPort);
		System.out.println("FROM ADDRESS SERVICE");
		return addressService.createAddress(createAddressRequest);
	}
	
	@GetMapping("/getById/{id}")
	public AddressResponse getById(@PathVariable long id) {
		System.out.println(serverPort);
		return addressService.getById(id);
	}
	
}
