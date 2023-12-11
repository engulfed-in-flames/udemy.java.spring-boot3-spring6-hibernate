package udemy.crudwithdb.student.dao;

import java.util.List;

import udemy.crudwithdb.student.entity.Student;

public interface StudentDAO {
	
	void save(Student student);
	
	Student findById(Integer id);
	
	List<Student> findAll();
	
	List<Student> findByLastName(String lastName);
	
	void update(Student student);
	
	void delete(int id);
	
	void deleteAll();
}
