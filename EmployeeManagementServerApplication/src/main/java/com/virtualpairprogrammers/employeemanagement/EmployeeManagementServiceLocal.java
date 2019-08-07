package com.virtualpairprogrammers.employeemanagement;

import java.util.List;

import javax.ejb.Local;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Local
public interface EmployeeManagementServiceLocal {

	public void registerEmployee(Employee employee) throws ServiceUnavailableException;
	public List<Employee> getAllEmployees();
	public List<Employee> searchBySurname(String surname);
	public Employee getById(int id);
}
