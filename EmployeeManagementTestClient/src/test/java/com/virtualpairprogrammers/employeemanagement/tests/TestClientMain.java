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
		
		List<Employee> employees = service.getAllEmployees();
		for (Employee employee: employees) {
			System.out.println(employee);
		}

	}

}
