package com.udemy.springboot.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udemy.springboot.cruddemo.dao.StudentDAO;
import com.udemy.springboot.cruddemo.entities.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
	@Bean 
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		
		// Exectued after the Spring Beans have been loaded
		return runner -> {
			// createStudent(studentDAO);
		
			 createMultipleSutdent(studentDAO);
			
			// printStudent(studentDAO, 1);
			
			// queryForStudents(studentDAO);
			
			// queryForStudentsByLastName(studentDAO, "laurent");
			
			// updateStudent(studentDAO, 1);
			
			// deleteStudent(studentDAO, 1);
			
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		
		System.out.println("Deleting all students...");
		studentDAO.deleteAll();
		System.out.println("Deleted all students completely");
	}

	private void deleteStudent(StudentDAO studentDAO, int i) {
		
		System.out.println("Deleting the student...");
		studentDAO.delete(i);
		System.out.println("Deleted the student completely");
	}

	private void updateStudent(StudentDAO studentDAO, Integer id) {
		
		System.out.println("Retrieving the student...");
		Student student = studentDAO.findById(id);
		
		System.out.println("Updating the student...");
		student.setFirstName("TOM");
		
		studentDAO.update(student);
		
		System.out.println("The updated student : " + student);
		
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {
		
		List<Student> students = studentDAO.findByLastName(lastName);
		
		for (Student student :students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		
		List<Student> students = studentDAO.findAll();
		
		for (Student student :students) {
			System.out.println(student);
		}
	}

	private void printStudent(StudentDAO studentDAO, Integer id) {
		Student student = studentDAO.findById(id);
		System.out.println("Found the student : "+student);
	}

	private void createMultipleSutdent(StudentDAO studentDAO) {
		
		// Create multiple students
		System.out.println("Creating student objects...");
		Student newStudent1 = new Student("Tom", "Brown", "Brown@udemy.com");
		Student newStudent2 = new Student("Saint", "Laurent", "Laurent@udemy.com");
		Student newStudent3 = new Student("Ballentino", "Garvani", "Garvani@udemy.com");
		
		// Save the students objects
		System.out.println("Saving the students...");
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		
		// Create the student object
		System.out.println("Creating new student object...");
		Student newStudent = new Student("John", "Jonhson", "johnson@udemy.com");
		
		// Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(newStudent);
		
		// Display id of the saved student
		System.out.println("Saved student. Generated ID : "+ newStudent.getId());
	}
}
