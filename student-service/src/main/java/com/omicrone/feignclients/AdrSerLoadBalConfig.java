package com.omicrone.feignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import feign.Feign;

@LoadBalancerClient(value = "address-service", configuration = CustomLoadBalancerConfiguration.class)
@CrossOrigin(origins = "http://localhost:8077")

public class AdrSerLoadBalConfig {
	@LoadBalanced
	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
