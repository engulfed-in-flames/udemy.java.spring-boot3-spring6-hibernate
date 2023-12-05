package com.udemy.springboot.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SexyRestController {

	// Inject custom properties
	@Value("${employer.name}")
	private String employerName;
	
	@Value("${company.name}")
	private String companyName;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping("/companyinfo")
	public String getCompanyInfo() {
		return "Company : "+companyName + " Employer : " +employerName;
	}
}
