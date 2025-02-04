package udemy.crudwithdb.student.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import udemy.crudwithdb.student.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO{

	// Define field for entity manager
	private EntityManager entityManager;
	
	// Inject entity manager using constructor injection
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	// Implement save method
	@Override
	@Transactional
	public void save(Student student) {
		
		entityManager.persist(student);
	}

	@Override
	public Student findById(Integer id) { 
		
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName", Student.class);
		
		return theQuery.getResultList();
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		
		TypedQuery<Student> theQuery = entityManager.createQuery(
				"FROM Student WHERE lastName=:param ORDER BY lastName", Student.class);
		
		theQuery.setParameter("param", lastName.toLowerCase());
		
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Student student) {
		
		entityManager.merge(student);
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		Student student = entityManager.find(Student.class, id);
		
		entityManager.remove(student);
	}

	@Override
	@Transactional
	public void deleteAll() {
		
		entityManager.createQuery("DELETE FROM Student").executeUpdate();
		// "Update" means "Modify" in DB
	}	
}
