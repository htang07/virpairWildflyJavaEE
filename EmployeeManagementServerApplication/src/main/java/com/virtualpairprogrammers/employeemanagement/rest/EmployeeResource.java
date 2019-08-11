package com.virtualpairprogrammers.employeemanagement.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
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
	
	//Returned Response type cannot handled corrected by JavaEE if returned content structure is application/XML
	@GET
	@Produces(value={"application/JSON", "application/XML"})
	public Response getAllEmployeesWhereIdBetween(@DefaultValue("0") @QueryParam("firstId") Integer firstId, @QueryParam("secondId") Integer secondId) {
		
		if (firstId == 0 && secondId == null) {
			GenericEntity<List<Employee>> employees = new GenericEntity<List<Employee>>(service.getAllEmployees()) {};
			return Response.ok(employees).build();
			//return Response.ok(service.getAllEmployees()).build(); //Returned Response type cannot handled corrected by JavaEE if returned content structure is application/XML
		}
		if (firstId != null && secondId != null) {
			GenericEntity<List<Employee>> employees = new GenericEntity<List<Employee>>(service.getAllEmployeesWhereIdBetween(firstId, secondId)) {};
			return Response.ok(employees).build();
			//return Response.ok(service.getAllEmployeesWhereIdBetween(firstId, secondId)).build();
		}
		return Response.status(400).build();
	}
	//this entry point is covered by getAllEmployeesWhereIdBetween method
	//http://localhost:8080/EmployeeManagementServerApplication/webservice/employees
//	@GET
//	@Produces(value = {"application/JSON","application/XML"})
//	public List<Employee> getAllEmployees() {
//		return service.getAllEmployees();
//	}
	
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
	@Produces({"application/JSON", "application/XML"})
	@Consumes(value={"application/JSON", "application/XML"})
	public Response createEmployee(Employee employee) {
		try {
			service.registerEmployee(employee);
			
			URI uri = null;
			try {
				uri = new URI("/employees/" + employee.getId());
			}
			catch (Exception e) {}
			
			return Response.created(uri).entity(employee).build();
		} catch (ServiceUnavailableException e) {
			return Response.status(504).build();
		}
	}
	

}
