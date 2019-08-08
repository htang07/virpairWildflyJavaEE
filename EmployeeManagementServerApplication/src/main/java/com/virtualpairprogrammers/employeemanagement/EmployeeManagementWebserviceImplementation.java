package com.virtualpairprogrammers.employeemanagement;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
@WebService(name="EmployeeManagementWebService")
public class EmployeeManagementWebserviceImplementation {

	@Inject
	private EmployeeManagementServiceLocal service;
	
	public Employee getEmployeeById(int id) {
		return service.getById(id);
	}
	
	public List<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	public void registerNewEmployee(Employee employee) throws ServiceUnavailableException {
		service.registerEmployee(employee);
	}
	
}
