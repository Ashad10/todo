package com.jspiders.project.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jspiders.project.entity.Employee;

@Component(value = "dao")
public class EmployeeDao {
	@Autowired
	private EntityManager entityManager;
	Scanner scanner = new Scanner(System.in);

	// Save the Employee Object which is passed
	public void saveEmployee(Employee employee) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityTransaction.commit();
		System.out.println("Employee saved");
	}

	public void save() {
		Employee employee = new Employee();
		System.out.println("Enter the Employee name:");
		employee.setName(scanner.next());
		System.out.println("Enter the Employee email:");
		employee.setEmail(scanner.next());
		System.out.println("Enter the Employee password:");
		employee.setPassword(scanner.next());
		System.out.println("Enter the Employee experience:");
		employee.setExperience(scanner.nextDouble());
		System.out.println("Enter the Employee salary:");
		employee.setSalary(scanner.nextDouble());
		System.out.println("Enter the Employee phone:");
		employee.setPhone(scanner.nextLong());
		saveEmployee(employee);
	}

	// Fetch the Employee Object based on Id
	public Employee getEmployee(int id) {
		Query q = entityManager.createQuery("select e from Employee e where e.id=?1");
		q.setParameter(1, id);
		return (Employee) q.getSingleResult();
	}

	public void fetch() {
		System.out.println("Enter the Employee Id:");
		int id = scanner.nextInt();
		try {
			System.out.println(getEmployee(id));
		} catch (NoResultException e) {
			System.err.println("No Employee Found!!!");
		}
	}

	// Update the Employee object which is passed
	public void updateEmployee(int id, Employee newEmployee) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		newEmployee.setId(id);
		entityManager.merge(newEmployee);
		entityTransaction.commit();
	}

	public void update() {
		System.out.println("enter the Employee id which needs to be updated:");
		int id = scanner.nextInt();
		Employee employee;
		try {
			getEmployee(id);
			employee = new Employee();
			System.out.println("Enter the Employee name:");
			employee.setName(scanner.next());
			System.out.println("Enter the Employee email:");
			employee.setEmail(scanner.next());
			System.out.println("Enter the Employee password:");
			employee.setPassword(scanner.next());
			System.out.println("Enter the Employee experience:");
			employee.setExperience(scanner.nextDouble());
			System.out.println("Enter the Employee salary:");
			employee.setSalary(scanner.nextDouble());
			System.out.println("Enter the Employee phone:");
			employee.setPhone(scanner.nextLong());
			updateEmployee(id, employee);
		} catch (NoResultException e) {
			System.err.println("No Employee found with that id. Can't be updated. Provide some other id");
		}
	}

	// Method to remove Employee based on Id
	public void removeEmployee(int id) {
		try {
			Employee employee = getEmployee(id);
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(employee);
			entityTransaction.commit();
			System.out.println("Employee got deleted");
		} catch (NoResultException e) {
			System.err.println("Id not found");
		}
	}

	public void remove() {
		System.out.println("Enter the id:");
		int id = scanner.nextInt();
		removeEmployee(id);
	}

	// Method to get All the Employees
	public List<Employee> getAllEmployee() {
		String query = "select e from Employee e";
		Query q = entityManager.createQuery(query);
		List<Employee> list = q.getResultList();
		return list;
	}

	public void allEmployee() {
		List<Employee> list = getAllEmployee();
		for (Employee emp : list) {
			System.out.println(emp.getName());
		}
	}
}
