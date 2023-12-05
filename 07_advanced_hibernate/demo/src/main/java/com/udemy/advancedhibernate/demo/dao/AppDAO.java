package com.udemy.advancedhibernate.demo.dao;

import java.util.List;

import com.udemy.advancedhibernate.demo.entity.Course;
import com.udemy.advancedhibernate.demo.entity.Instructor;
import com.udemy.advancedhibernate.demo.entity.InstructorDetail;
import com.udemy.advancedhibernate.demo.entity.Student;

public interface AppDAO {

	// Instructor
	void save(Instructor instructor);
	
	Instructor findInstructorById(int id);
	
	Instructor findInstructorWithCourses(int id);
	
	void updateInstructor(Instructor instructor);
	
	void deleteInstructorById(int id);
	
	// InstructorDetail
	InstructorDetail findInstructorDetailById(int id);
	
	void deleteInstructorDetailById(int id);
	
	// Course
	void save(Course course);
	
	Course findCourseById(int id);
	
	Course findCourseWithReviewsById(int id);
	
	void updateCourse(Course course);
	
	void deleteCourseById(int id);
	
	List<Course> findCoursesByInstructorId(int id);
	
	Course findCourseWithStudentsById(int id);
	
	// Student
	Student findStudentWithCourseById(int id);
	
	void updateStudent(Student student);
	
	void deleteStudentById(int id);
}
