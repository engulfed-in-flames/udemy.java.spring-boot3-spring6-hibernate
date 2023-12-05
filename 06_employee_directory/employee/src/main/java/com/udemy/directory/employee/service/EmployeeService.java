package com.udemy.directory.employee.service;

import java.util.List;

import com.udemy.directory.employee.entity.Employee;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
