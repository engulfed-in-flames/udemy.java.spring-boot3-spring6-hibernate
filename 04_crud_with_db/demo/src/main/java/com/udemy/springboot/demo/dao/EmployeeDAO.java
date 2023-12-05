package com.udemy.springboot.demo.dao;

import java.util.List;

import com.udemy.springboot.demo.entity.Employee;

public interface EmployeeDAO {
	
	Employee save(Employee employee);
	
	List<Employee> findAll();
	
	Employee findById(int id);
	
	void deleteById(int id);
}
