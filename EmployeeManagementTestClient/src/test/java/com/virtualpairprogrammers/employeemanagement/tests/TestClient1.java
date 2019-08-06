package com.virtualpairprogrammers.employeemanagement.tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.virtualpairprogrammers.employeemanagement.EmployeeManagementService;
import com.virtualpairprogrammers.employeemanagement.domain.Employee;

public class TestClient1 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTransactionRollback() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context",true); //wildfly naming structure
		
		Context jndi = new InitialContext(jndiProperties);
		
		EmployeeManagementService service = (EmployeeManagementService)jndi.lookup("EmployeeManagementServerApplication/EmployeeManagementImplementation!com.virtualpairprogrammers.employeemanagement.EmployeeManagementService");
		Employee employee1 = new Employee("Mike","Thomas","Engineer",1000);
		try {
			service.enrollEmployeeHandleRollback(employee1);
		}catch(Exception e) {
			System.out.println("Something went wrong");
		}
		

		List<Employee> employees = service.getAllEmployees();
		for (Employee employee: employees) {
			System.out.println(employee);
		}
		
	}

}
