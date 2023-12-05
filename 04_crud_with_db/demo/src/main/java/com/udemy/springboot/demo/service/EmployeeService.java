package com.udemy.springboot.demo.service;

import java.util.List;

import com.udemy.springboot.demo.entity.Employee;

public interface EmployeeService {

	Employee save(Employee employee);
	
	List<Employee> findAll();
	
	Employee findById(int id);
	
	void deleteById(int id);
}
