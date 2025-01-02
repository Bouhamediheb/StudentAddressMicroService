package com.omicrone.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.omicrone.entity.Address;
import com.omicrone.exceptions.AdressNotFoundException;
import com.omicrone.repository.AddressRepository;
import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;

@Service
public class AddressService {

	Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressRepository addressRepository;

	
	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		
		Address address = new Address();
		address.setStreet(createAddressRequest.getStreet());
		address.setCity(createAddressRequest.getCity());
		
		addressRepository.save(address);
		
		return new AddressResponse(address);
		
	}
	
	public AddressResponse getById (long id) {
		
		Optional<Address> addressOpt = addressRepository.findById(id);
		if(!addressOpt.isPresent())
			throw new AdressNotFoundException("Adresse inexistante ::: ");
		return new AddressResponse(addressOpt.get());
	}

	public List<AddressResponse> getAllAddress() {
		return addressRepository.findAll()
				.stream()
				.map(AddressResponse::new)
				.collect(Collectors.toList());
	}
	
	
}
