package com.udemy.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
			scanBasePackages = {
					"com.udemy.springboot.demo",
					"com.udemy.springboot.temp"
					}
		)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
