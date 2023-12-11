package udemy.crudwithrest.employee.service;

import java.util.List;

import udemy.crudwithrest.employee.entity.Employee;

public interface EmployeeService {

	Employee save(Employee employee);

	List<Employee> findAll();

	Employee findById(int id);

	void deleteById(int id);
}