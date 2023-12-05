package com.udemy.springboot.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomConstraintValidator implements ConstraintValidator<CustomConstraint, String> {

	
	private String prefix;
	
	@Override
	public void initialize(CustomConstraint customConstraint) {
		prefix = customConstraint.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return false;
		}
		
		return value.startsWith(prefix);
	}
}
