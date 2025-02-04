package com.udemy.advancedhibernate.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udemy.advancedhibernate.demo.dao.AppDAO;
import com.udemy.advancedhibernate.demo.entity.Course;
import com.udemy.advancedhibernate.demo.entity.Instructor;
import com.udemy.advancedhibernate.demo.entity.InstructorDetail;
import com.udemy.advancedhibernate.demo.entity.Review;
import com.udemy.advancedhibernate.demo.entity.Student;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	// Executed after the Spring Beans have been loaded
	@Bean
	public CommandLineRunner runner(AppDAO dao) {
		
		return runner -> {
			// createInstructors(dao);
			// deleteInstructorById(dao, 1);
			// deleteInstructorById(dao, 2);
			// printInstructorFromInstructorDetailById(dao, 1);
			// deleteInstructorDetailById(dao, 2);
			// createInstructorsWithCourses(dao);
			// findInstructorWithCourses(dao, 1);
			// findInstructorWithCoursesByJoinFetch(dao, 1);
			// updateInstructor(dao, 1);
			// deleteInstructor(dao, 1);
			// updateCourse(dao, 2);
			// deleteCourse(dao, 2);
			// createCourseWithReviews(dao);
			// findCourseWithReviews(dao, 5);
			// createCourseAndStudents(dao);
			// findCourseWithStudents(dao, 6);
			// findStudentWithCourses(dao, 1);
			// addMoreCoursesForStudent(dao, 1);
			// deleteCourseV2(dao, 8);
			deleteStudent(dao, 2);
		};
	}
	
	private void findInstructorWithCoursesByJoinFetch(AppDAO dao, int id) {
		
		Instructor instructor = dao.findInstructorWithCourses(id);
		
		System.out.println("The instructor: " + instructor);
		System.out.println("The instructor's detail: " + instructor.getInstructorDetail());
		System.out.println("The associated courses: " + instructor.getCourses());
	}

	// One To One
	private void createInstructors(AppDAO dao) {
		Instructor instructor1 = new Instructor("Chad", "Darby", "darby@udemy.com");
		
		InstructorDetail instructorDetail1= new InstructorDetail(
						"http://www.udemy.com/youtube",
						"Playing games"
				);
		
		Instructor instructor2 = new Instructor("Madhu", "Patel", "madhu@udemy.com");
		
		InstructorDetail instructorDetail2 = new InstructorDetail(
						"http://www.udemy.com/youtube",
						"Playing the piano"
				);
		
		instructor1.setInstructorDetail(instructorDetail1);
		dao.save(instructor1);
		
		instructor2.setInstructorDetail(instructorDetail2);
		dao.save(instructor2);
	}
	
	private void deleteInstructorById(AppDAO dao, int id) {
		
		dao.deleteInstructorById(id);
	}
	
	private void printInstructorFromInstructorDetailById(AppDAO dao, int id) {
		
		InstructorDetail instructorDetail = dao.findInstructorDetailById(id);
		Instructor instructor = instructorDetail.getInstructor();
		System.out.println("ID: " + id);
		System.out.println(instructor);
	}
	
	private void deleteInstructorDetailById(AppDAO dao, int id) {
		
		dao.deleteInstructorDetailById(id);
		
		// If you want to break bi-directional relationship,
		// 1. Change the cascade type
		// 2. Set the associated entity's FK column to NULL
	}
	
	// One To Many
	private void createInstructorsWithCourses(AppDAO dao) {
		
		// In advance, truncate data in all tables.
		
		Instructor instructor1 = new Instructor("susan", "Anby", "susan@udemy.com");
		
		InstructorDetail instructorDetail1= new InstructorDetail(
						"http://www.udemy.com/youtube",
						"Guitar"
				);
		instructor1.setInstructorDetail(instructorDetail1);
		
		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("The Pinball Master Class");
		
		instructor1.addCourse(course1);
		instructor1.addCourse(course2);
		
		System.out.println("Saved instructor: " + instructor1);
		System.out.println("The courses: " + instructor1.getCourses());
		
		dao.save(instructor1);
	}
	
	// Fetch Type
	private void findInstructorWithCourses(AppDAO dao, int id) {

		Instructor instructor = dao.findInstructorById(id);
		
		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + dao.findCoursesByInstructorId(id));
	}
	
	// Update & Delete
	private void updateInstructor(AppDAO dao, int id) {
		
		Instructor instructor = dao.findInstructorById(id);
		instructor.setLastName("Updated");
		
		dao.updateInstructor(instructor);
	}
	
	private void deleteInstructor(AppDAO dao, int id) {
		
		dao.deleteInstructorById(id);
	}
	
	private void deleteCourse(AppDAO dao, int id) {
		
		dao.deleteCourseById(id);
	}

	private void updateCourse(AppDAO dao, int id) {
		
		Course course = dao.findCourseById(id);
		course.setTitle("Updated Title");
		
		dao.updateCourse(course);
	}
	
	private void createCourseWithReviews(AppDAO dao) {
		
		Course course = new Course("Pacman - How to Score One Million Poinsts");
		course.addReview(new Review("Great course!"));
		course.addReview(new Review("Good course!"));
		course.addReview(new Review("Excellent course!"));
		course.addReview(new Review("Bad course!"));
		
		System.out.println("The course: " + course);
		System.out.println("The associated reviews: " + course.getReviews());
		
		dao.save(course);
	}

	private void findCourseWithReviews(AppDAO dao, int id) {
		Course course = dao.findCourseWithReviewsById(id);
		
		System.out.println("The course: " + course);
		System.out.println("The associated reviews: " + course.getReviews());
		
	}
	
	private void createCourseAndStudents(AppDAO dao) {
		Course course1 = new Course("Youtuber - How to Get One Million Subscriptions");
		
		Student student1 = new Student("John", "Doe", "john@udemy.com");
		Student student2 = new Student("Mary", "Jane", "mary@udemy.com");
		
		course1.addStudent(student1);
		course1.addStudent(student2);
		
		dao.save(course1);
	}
	
	private void findCourseWithStudents(AppDAO dao, int id) {
		
		Course course = dao.findCourseWithStudentsById(id);
		
		System.out.println("The course: " + course);
		System.out.println("The associated students: " + course.getStudents());
	}

	private void findStudentWithCourses(AppDAO dao, int id) {
		
		Student student = dao.findStudentWithCourseById(id);
		
		System.out.println("The student: " + student);
		System.out.println("The associated reviews: " + student.getCourses());
	}

	private void addMoreCoursesForStudent(AppDAO dao, int id) {
		
		Student student = dao.findStudentWithCourseById(id);
		
		student.addCourse(new Course("Rubik's Cube - How to Speed Cube"));
		student.addCourse(new Course("Atari 2600 - Game Development"));
		
		dao.updateStudent(student);
	}
	
	private void deleteCourseV2(AppDAO dao, int id) {
		
		// Only deletes data in studet_course table.
		dao.deleteCourseById(id);
	}

	private void deleteStudent(AppDAO dao, int id) {
	
		dao.deleteStudentById(id);
	}
}
