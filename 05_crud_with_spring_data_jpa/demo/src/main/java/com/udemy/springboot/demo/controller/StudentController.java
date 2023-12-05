package com.udemy.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udemy.springboot.demo.model.Student;

@Controller
public class StudentController {
	
	@Value("${countries}")
	private List<String> countries;
	
	@Value("${languages}")
	private List<String> languages;
	
	@Value("${operatingSystems}")
	private List<String> operatingSystems;

	@RequestMapping(path="/show-student-form", method=RequestMethod.GET)
	public String showStudentForm(Model model) {
		
		Student student = new Student();
		
		model.addAttribute("student", student);
		model.addAttribute("countries", countries);
		model.addAttribute("languages", languages);
		model.addAttribute("operatingSystems", operatingSystems);
		
		return "studentForm";
	}
	
	@RequestMapping(path="/process-student-form", method=RequestMethod.POST)
	public String processStudentForm(@ModelAttribute("student") Student student) {
		
		return "helloStudent";
	}
}
