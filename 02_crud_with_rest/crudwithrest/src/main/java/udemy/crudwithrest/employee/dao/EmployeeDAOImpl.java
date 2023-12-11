package udemy.crudwithrest.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import udemy.crudwithrest.employee.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Employee save(Employee employee) {

		// if id == 0, save. Otherwise, update.
		Employee savedEmployee = entityManager.merge(employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> findAll() {

		TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int id) {

		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public void deleteById(int id) {

		Employee employee = entityManager.find(Employee.class, id);
		entityManager.remove(employee);
	}
}
