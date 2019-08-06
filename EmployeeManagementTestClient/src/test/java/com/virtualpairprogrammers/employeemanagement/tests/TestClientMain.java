package com.virtualpairprogrammers.employeemanagement.tests;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.virtualpairprogrammers.employeemanagement.EmployeeManagementService;
import com.virtualpairprogrammers.employeemanagement.domain.Employee;

public class TestClientMain {

	public static void main(String[] args) throws NamingException{
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context",true); //wildfly naming structure
		
		Context jndi = new InitialContext(jndiProperties);
		
		EmployeeManagementService service = (EmployeeManagementService)jndi.lookup("EmployeeManagementServerApplication/EmployeeManagementImplementation!com.virtualpairprogrammers.employeemanagement.EmployeeManagementService");
//		Employee employee1 = new Employee("Mike","Thomas","Engineer",1000);
//		service.registerEmployee(employee1);
//		Employee emp1 = new Employee("Sophie","Green","Artist",1700);
//		Employee emp2 = new Employee("Ronald", "Smith", "Conductor", 1900);
//		Employee emp3 = new Employee("Eric", "Jones", "Pianist", 2000);
//		service.registerEmployee(emp1);
//		service.registerEmployee(emp2);
//		service.registerEmployee(emp3);
//		System.out.println("find by surname: " + service.searchBySurname("Green"));
//		System.out.println("****************************************");
		List<Employee> employees = service.getAllEmployees();
		for (Employee employee: employees) {
			System.out.println(employee);
		}
		
	}

}
