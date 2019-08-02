package com.virtualpairprogrammers.employeemanagement;

import java.util.List;

import javax.ejb.Remote;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Remote
public interface EmployeeManagementService {

	public void registerEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public List<Employee> searchBySurname(String surname);
}
