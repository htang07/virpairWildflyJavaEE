package com.virtualpairprogrammers.employeemanagement.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.virtualpairprogrammers.employeemanagement.EmployeeManagementServiceLocal;
import com.virtualpairprogrammers.employeemanagement.ServiceUnavailableException;
import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
@Path("/employees")
public class EmployeeResource {

	@Inject
	private EmployeeManagementServiceLocal service;
	
	//http://localhost:8080/EmployeeManagementServerApplication/webservice/employees
	@GET
	@Produces("application/XML")
	public List<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	//ex. http://localhost:8080/EmployeeManagementServerApplication/webservice/employees
	@GET
	@Produces("application/XML")
	@Path("{employeeNo}")
	public Employee findEmployeeById(@PathParam("employeeNo") int id) {
		return service.getById(id);
	}
	
	@POST
	@Produces("application/XML")
	@Consumes("application/XML")
	public Employee createEmployee(Employee employee) {
		try {
			service.registerEmployee(employee);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
