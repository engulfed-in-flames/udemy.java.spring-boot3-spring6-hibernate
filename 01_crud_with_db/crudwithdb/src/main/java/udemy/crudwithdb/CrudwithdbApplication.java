package udemy.crudwithdb;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import udemy.crudwithdb.student.dao.StudentDAO;
import udemy.crudwithdb.student.entity.Student;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
// You can scan packages located in parent folders by using 'scanBasePackages' property
@SpringBootApplication
public class CrudwithdbApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(CrudwithdbApplication.class, args);
		checkBeansPresence("cricketCoach", "cricketPlayer");
	}
	
	@Bean
	public CommandLineRunner runner(StudentDAO dao) {
		
		return runner -> {
			System.out.println("---- ---- CommandLineRunner ---- ----");
			// createStudent(studentDAO);
			
			// createMultipleSutdent(studentDAO);
			
			// printStudent(studentDAO, 1);
			
			// queryForStudents(studentDAO);
			
			// queryForStudentsByLastName(studentDAO, "laurent");
			
			// updateStudent(studentDAO, 1);
			
			// deleteStudent(studentDAO, 1);
			
			// deleteAllStudents(studentDAO);
		};
	}

	// Check if the bean exists
	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " + context.containsBean(beanName));
		}
	}
	
	private void createStudent(StudentDAO studentDAO) {
		
		System.out.println("Creating new student object...");
		Student newStudent = new Student("John", "Jonhson", "johnson@udemy.com");
		
		System.out.println("Saving the student...");
		studentDAO.save(newStudent);
		
		System.out.println("Saved student. Generated ID : "+ newStudent.getId());
	}
	
	private void createMultipleSutdent(StudentDAO studentDAO) {
		
		System.out.println("Creating student objects...");
		Student newStudent1 = new Student("Tom", "Brown", "Brown@udemy.com");
		Student newStudent2 = new Student("Saint", "Laurent", "Laurent@udemy.com");
		Student newStudent3 = new Student("Ballentino", "Garvani", "Garvani@udemy.com");
		
		System.out.println("Saving the students...");
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);
	}
	
	private void printStudent(StudentDAO studentDAO, Integer id) {
		
		Student student = studentDAO.findById(id);
		System.out.println("Found the student : "+student);
	}
	
	private void queryForStudents(StudentDAO studentDAO) {
		
		List<Student> students = studentDAO.findAll();
		
		for (Student student :students) {
			System.out.println(student);
		}
	}
	
	private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {
		
		List<Student> students = studentDAO.findByLastName(lastName);
		
		for (Student student :students) {
			System.out.println(student);
		}
	}
	
	private void updateStudent(StudentDAO studentDAO, Integer id) {
		
		System.out.println("Retrieving the student...");
		Student student = studentDAO.findById(id);
		
		System.out.println("Updating the student...");
		student.setFirstName("TOM");
		
		studentDAO.update(student);
		
		System.out.println("The updated student : " + student);
	}
	
	private void deleteStudent(StudentDAO studentDAO, int i) {
		
		System.out.println("Deleting the student...");
		studentDAO.delete(i);
		System.out.println("Deleted the student completely");
	}
	
	private void deleteAllStudents(StudentDAO studentDAO) {
		
		System.out.println("Deleting all students...");
		studentDAO.deleteAll();
		System.out.println("Deleted all students completely");
	}
	
}
