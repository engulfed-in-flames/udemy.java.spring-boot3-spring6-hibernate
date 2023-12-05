package com.udemy.springboot.temp;

import org.springframework.stereotype.Component;

@Component
public class CricketPlayer implements Player{

	private String name="George";
	
	@Override
	public String getName() {
		return name;
	}
}
