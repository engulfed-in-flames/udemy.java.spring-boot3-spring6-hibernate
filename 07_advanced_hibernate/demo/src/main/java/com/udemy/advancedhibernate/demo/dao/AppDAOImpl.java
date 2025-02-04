package com.udemy.advancedhibernate.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.udemy.advancedhibernate.demo.entity.Course;
import com.udemy.advancedhibernate.demo.entity.Instructor;
import com.udemy.advancedhibernate.demo.entity.InstructorDetail;
import com.udemy.advancedhibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	EntityManager entityManager;
	
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Instructor
	@Override
	@Transactional
	public void save(Instructor instructor) {
		
		entityManager.persist(instructor);
	}
	
	@Override
	public Instructor findInstructorById(int id) {
		
		return entityManager.find(Instructor.class, id);
	}
	
	@Override
	@Transactional
	public void updateInstructor(Instructor instructor) {
		
		entityManager.merge(instructor);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		
		// When course entity doesn't exist
		// Instructor instructor = findInstructorById(id);
		
		// When course entity exists
		Instructor instructor = entityManager.find(Instructor.class, id);
		List<Course> courses = instructor.getCourses();
		
		for (Course course : courses) {
			course.setInstructor(null);
		}
		
		entityManager.remove(instructor);
	}

	// InstructorDetail
	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		
		return entityManager.find(InstructorDetail.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int id) {
		
		InstructorDetail instructorDetail = findInstructorDetailById(id);
		
		entityManager.remove(instructorDetail);
	}
	
	// Course
	@Override
	@Transactional
	public void save(Course course) {
		
		// Save a course and associated reviews due to cascade type
		entityManager.persist(course);
	}

	
	@Override
	public Course findCourseById(int id) {
	
		return entityManager.find(Course.class, id);
	}
	
	
	@Override
	public Course findCourseWithReviewsById(int id) {
		
		TypedQuery<Course> query = entityManager.createQuery(
						"select c from Course c "
						+ "JOIN FETCH c.reviews "
						+ "where c.id = :data"
				, Course.class);
		query.setParameter("data", id);
		
		Course course = query.getSingleResult();
		
		return course;
	}

	@Override
	public List<Course> findCoursesByInstructorId(int id) {
		
		TypedQuery<Course> query = entityManager.createQuery(
						"from Course where instructor.id = :data", Course.class
						);
		query.setParameter("data", id);
		
		List<Course> courses = query.getResultList();
		return courses;
	}
	
	
	@Override
	public Instructor findInstructorWithCourses(int id) {
		
		TypedQuery<Instructor> query = entityManager.createQuery(
						"select i from Instructor i "
						+ "JOIN FETCH i.courses "
						+ "where i.id = :data",
						Instructor.class);
		query.setParameter("data", id);
		
		Instructor instructor = query.getSingleResult();
		
		return instructor;
	}
	
	
	@Override
	public Course findCourseWithStudentsById(int id) {
		
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c "
				+ "JOIN FETCH c.students "
				+ "where c.id = :data",
				Course.class);
		query.setParameter("data", id);
		
		Course course = query.getSingleResult();
		
		return course;
	}

	@Override
	@Transactional
	public void updateCourse(Course course) {
		
		entityManager.merge(course);
	}

	@Override
	@Transactional
	public void deleteCourseById(int id) {
		
		Course course = entityManager.find(Course.class, id);
		
		entityManager.remove(course);
	}
	
	// Student
	@Override
	public Student findStudentWithCourseById(int id) {
		TypedQuery<Student> query = entityManager.createQuery(
				"select s from Student s "
				+ "JOIN FETCH s.courses "
				+ "where s.id = :data",
				Student.class);
		query.setParameter("data", id);
		
		Student student = query.getSingleResult();
		
		return student;
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		
		entityManager.merge(student);
	}

	@Override
	@Transactional
	public void deleteStudentById(int id) {
		
		Student student = entityManager.find(Student.class, id);
		
		entityManager.remove(student);
	}
	
	
}
