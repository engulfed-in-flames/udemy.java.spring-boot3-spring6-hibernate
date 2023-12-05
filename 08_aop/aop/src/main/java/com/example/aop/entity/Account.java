package com.example.aop.entity;


public class Account {

	public enum Level {VIP, PLATINUM, GOLD, SIVLER, BROZNE}
	
	private String name;
	private Level level;
	
	public Account() {};
	
	public Account(String name, Level level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "Account [name=" + name + ", level=" + level + "]";
	}
}
