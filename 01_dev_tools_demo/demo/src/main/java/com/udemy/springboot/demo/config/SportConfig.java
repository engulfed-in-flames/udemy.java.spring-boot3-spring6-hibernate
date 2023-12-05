package com.udemy.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.springboot.demo.classes.Coach;
import com.udemy.springboot.demo.classes.SwimCoach;

@Configuration
public class SportConfig {
	
	@Bean("aquatic")
	public Coach swimCoach() {
		return new SwimCoach();
	}
}
