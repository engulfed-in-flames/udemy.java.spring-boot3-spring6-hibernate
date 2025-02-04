package udemy.crudwithrest.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.crudwithrest.employee.entity.Employee;
import udemy.crudwithrest.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("")
	public List<Employee> getEmployees() {

		return employeeService.findAll();
	};

	@PostMapping("")
	public Employee addEmployee(@RequestBody Employee employee) {

		Employee savedEmployee = employeeService.save(employee);

		return savedEmployee;
	}

	@PutMapping("")
	public Employee updateEmployee(@RequestBody Employee employee) {

		Employee updatedEmployee = employeeService.save(employee);

		return updatedEmployee;
	}

	@GetMapping("{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);

		if (employee == null) {
			throw new RuntimeException("Employee ID " + employeeId + " not found.");
		}

		return employee;
	}

	@DeleteMapping("{employeeId}")
	public void deleteEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);

		if (employee == null) {
			throw new RuntimeException("Employee ID " + employeeId + " not found.");
		}

		employeeService.deleteById(employeeId);
	}
}
