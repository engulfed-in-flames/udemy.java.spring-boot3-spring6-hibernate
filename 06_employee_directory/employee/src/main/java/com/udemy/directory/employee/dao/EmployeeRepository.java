package com.udemy.directory.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.directory.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findAllByOrderByLastNameAsc();
}