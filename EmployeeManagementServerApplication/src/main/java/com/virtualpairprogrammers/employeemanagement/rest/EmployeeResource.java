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
import javax.ws.rs.core.Response;

import com.virtualpairprogrammers.employeemanagement.EmployeeManagementServiceLocal;
import com.virtualpairprogrammers.employeemanagement.ServiceUnavailableException;
import com.virtualpairprogrammers.employeemanagement.dataaccess.EmployeeNotFoundException;
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
	public Response findEmployeeById(@PathParam("employeeNo") int id, @Context HttpHeaders headers) {
		System.out.println("requested headers: " + headers.getRequestHeaders());
	
		try {
			Employee result = service.getById(id);
			return Response.ok(result).build();
		} catch (EmployeeNotFoundException e) {
			
			System.out.println(e.getMessage());
			
			e.printStackTrace();
			return Response.status(404).build();
		}
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
