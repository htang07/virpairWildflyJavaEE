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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

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
	@Produces(value = {"application/JSON","application/XML"})
	public List<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	//ex. http://localhost:8080/EmployeeManagementServerApplication/webservice/employees
	@GET
	@Produces(value = {"application/JSON","application/XML"})
	@Path("{employeeNo}")
	public Employee findEmployeeById(@PathParam("employeeNo") int id, @Context HttpHeaders headers) {
		System.out.println("requested headers: " + headers.getRequestHeaders());
		return service.getById(id);
	}
	
	@POST
	@Produces(value = {"application/JSON","application/XML"})
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
