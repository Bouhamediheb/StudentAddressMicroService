package com.omicrone.feignclients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;

@org.springframework.cloud.openfeign.FeignClient(value = "apigateway")
public interface FeignClient {

    @GetMapping("/api/address/getById/{id}")
    AddressResponse getById(@PathVariable long id);

    @PostMapping("/api/address/create")
    AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest);
}

