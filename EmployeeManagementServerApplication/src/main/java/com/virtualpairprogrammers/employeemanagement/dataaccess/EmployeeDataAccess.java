package com.virtualpairprogrammers.employeemanagement.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Local
public interface EmployeeDataAccess {

	public abstract void insert(Employee newEmployee);

	public abstract List<Employee> findAll();

	public abstract List<Employee> findBySurname(String surname);
	
	public abstract Employee findById(int id) throws EmployeeNotFoundException;
	
	public abstract List<Employee> getAllEmployeesWhereIdBetween(int firstId,
			int secondId);
	
	void updateEmployee(int id, String jobRole, int salary)throws EmployeeNotFoundException;
	
	void deleteEmployee(int id) throws EmployeeNotFoundException;

}