package com.udemy.springboot.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CustomConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomConstraint {

	// Define default custom constraint
	public String value() default "LUV";
	
	// Define default error message
	public String message() default "must start with LUV";
	
	// Define default groups
	public Class<?>[] groups() default {};
	
	// Define default payloads
	public Class<? extends Payload>[] payload() default {};
}
