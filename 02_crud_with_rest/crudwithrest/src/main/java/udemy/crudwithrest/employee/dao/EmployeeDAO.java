package udemy.crudwithrest.employee.dao;

import java.util.List;

import udemy.crudwithrest.employee.entity.Employee;

public interface EmployeeDAO {

	Employee save(Employee employee);

	List<Employee> findAll();

	Employee findById(int id);

	void deleteById(int id);
}
