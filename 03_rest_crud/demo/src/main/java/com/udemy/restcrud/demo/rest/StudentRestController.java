package com.udemy.restcrud.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.restcrud.demo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students;
	
	@PostConstruct
	public void loadData() {
		
		students = new ArrayList<Student>();
		
		students.add(new Student("Tom", "Brown"));
		students.add(new Student("Saint", "Laurent"));
		students.add(new Student("Valentino", "Garvani"));
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
	
		return students;
	}
	
	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		if(studentId < 0 || studentId >= students.size()) {
			throw new StudentNotFoundExceiption("Student id " + studentId + " not found.");
		}
		
		return students.get(studentId);
	}
}

