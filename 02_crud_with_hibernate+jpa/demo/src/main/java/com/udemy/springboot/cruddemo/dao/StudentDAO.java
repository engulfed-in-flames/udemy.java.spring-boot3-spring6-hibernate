package com.udemy.springboot.cruddemo.dao;

import java.util.List;

import com.udemy.springboot.cruddemo.entities.Student;

public interface StudentDAO {
	
	void save(Student student);
	
	Student findById(Integer id);
	
	List<Student> findAll();
	
	List<Student> findByLastName(String lastName);
	
	void update(Student student);
	
	void delete(Integer id);
	
	void deleteAll();
}
