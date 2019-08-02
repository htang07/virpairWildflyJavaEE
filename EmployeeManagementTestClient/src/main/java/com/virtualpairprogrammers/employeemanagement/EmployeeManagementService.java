package com.virtualpairprogrammers.employeemanagement;


import java.util.List;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;


public interface EmployeeManagementService {

	public void registerEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public List<Employee> searchBySurname(String surname);
}
