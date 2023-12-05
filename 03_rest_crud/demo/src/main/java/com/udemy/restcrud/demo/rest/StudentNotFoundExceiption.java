package com.udemy.restcrud.demo.rest;

public class StudentNotFoundExceiption extends RuntimeException{

	public StudentNotFoundExceiption(
			String message, 
			Throwable cause, 
			boolean enableSuppression,
			boolean writableStackTrace
			) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StudentNotFoundExceiption(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentNotFoundExceiption(String message) {
		super(message);
	}

	
}
