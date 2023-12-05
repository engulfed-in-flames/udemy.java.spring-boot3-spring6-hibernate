package com.udemy.directory.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// Spring Security
	@GetMapping("/showLoginForm")
	public String showLoginForm() {
		
		return "loginForm";
	}
	
	@GetMapping("/managers")
	public String showManagerOnlyPage() {
		
		return "managerOnly";
	}
	
	@GetMapping("/admins")
	public String showAdminOnlyPage() {
		
		return "adminOnly";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		
		return "accessDenied";
	}
}
