package com.udemy.directory.employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udemy.directory.employee.entity.Employee;
import com.udemy.directory.employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	// @Autowired <- Optional
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("")
	public String showEmployees(Model model) {
		
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		
		return "employees/employees";
	}
	
	@GetMapping("register")
	public String showEmployeeRegistrationForm(Model model) {
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "employees/registrationForm";
	}
	
	@PostMapping("register")
	public String registerEmployee(@ModelAttribute("employee") Employee employee) {
		
		employeeService.save(employee);
		
		return "redirect:/employees";
	}
	
	@GetMapping("{id}/update")
	public String showEmployeeUpdateForm(@PathVariable("id") int employeeId, Model model) {
		
		Employee employee = employeeService.findById(employeeId);
		model.addAttribute(employee);
		
		return "employees/updateForm";
	}
	
	@PostMapping("update")
	public String updateEmployee(@ModelAttribute("employee") Employee employee) {
		
		employeeService.save(employee);
		
		return "redirect:/employees";
	}

	@GetMapping("{id}/delete")
	public String deleteEmployee(@PathVariable("id")  int employeeId) {
		
		employeeService.deleteById(employeeId);
		
		return "redirect:/employees";
	}
}
