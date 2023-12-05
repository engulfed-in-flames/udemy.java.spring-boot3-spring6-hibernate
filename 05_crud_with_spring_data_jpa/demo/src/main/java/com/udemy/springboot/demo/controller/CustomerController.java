package com.udemy.springboot.demo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.udemy.springboot.demo.model.Customer;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		
		// if true, trim to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/show-customer-form")
	public String showCustomerForm(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customerForm";
	}
	
	@PostMapping("/process-customer-form")
	public String processCustomerForm(
			@Valid @ModelAttribute("customer") Customer customer, 
			BindingResult bindingResult) {
		
		// For debug
		System.out.println("Binding results: " + bindingResult.toString());
		System.out.println("\n\n\n\n");
		
		if(bindingResult.hasErrors()) {
			return "customerForm";
		} else {
			return "helloCustomer";
		}
	}
}
