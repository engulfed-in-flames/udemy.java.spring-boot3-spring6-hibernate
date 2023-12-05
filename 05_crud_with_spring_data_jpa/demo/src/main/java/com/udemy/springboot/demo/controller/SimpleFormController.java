package com.udemy.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SimpleFormController {

	/*
	// By default, @RequestMapping handles all HTTP methods.
	@RequestMapping("/show-form")
	public String showForm() {
		return "showForm";
	}
	*/
	
	// This mapping only handles GET method.
	/*
	@GetMapping("/show-form")
	public String showForm() {
		return "showForm";
	}
	*/
	
	// This mapping only handles GET method.
	@RequestMapping(path="/show-form", method=RequestMethod.GET)
	public String showForm() {
		return "showForm";
	}
	
	@RequestMapping("/process-form")
	public String processForm() {
		return "helloWorld";
	}
	
	@RequestMapping("/process-form-v2")
	public String processFormV2(HttpServletRequest request, Model model) {
		
		String name = request.getParameter("studentName");
		name = name.toUpperCase();
		String result = "Yo! " + name;
		model.addAttribute("message", result);
		
		return "helloWorld";
	}
	
	@RequestMapping(path="/process-form-v3", method=RequestMethod.POST)
	public String processFormV3(@RequestParam("studentName") String name, Model model) {
		
		name = name.toUpperCase();
		String result = "Yo! " + name;
		model.addAttribute("message", result);
		
		return "helloWorld";
	}
	
}
