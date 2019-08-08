import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import generatedclasses.com.virtualpairprogrammers.employeemanagement.Employee;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.EmployeeManagementWebService;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.EmployeeManagementWebserviceImplementationService;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.ServiceUnavailableException_Exception;

public class SoapWebServiceClientTest1 {

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
	public void testGetEmployees() {
		EmployeeManagementWebService service= new EmployeeManagementWebserviceImplementationService().getEmployeeManagementWebServicePort();
		List<Employee> employees=service.getAllEmployees();
		employees.forEach(x-> System.out.println("id: " + x.getId() + " firstname: " + x.getFirstName() + " salary: " + x.getSalary()));
	}
	
	@Test
	public void testGetEmployeeById() {
		EmployeeManagementWebService service= new EmployeeManagementWebserviceImplementationService().getEmployeeManagementWebServicePort();
		Employee employee1 = service.getEmployeeById(1);
		System.out.println("id: " + employee1.getId() + " firstname: " + employee1.getFirstName() + " salary: " + employee1.getSalary());
	}
	
	@Test
	public void testRegisterNewEmployee() throws ServiceUnavailableException_Exception {
		EmployeeManagementWebService service= new EmployeeManagementWebserviceImplementationService().getEmployeeManagementWebServicePort();
		
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("Robert");
		//newEmployee.setId(11);
		newEmployee.setJobRole("coach");
		newEmployee.setSalary(2000);
		
		List<Employee> existingEmployees=service.getAllEmployees();
		existingEmployees.forEach(x-> System.out.println("id: " + x.getId() + " firstname: " + x.getFirstName() + " salary: " + x.getSalary()));
		
		service.registerNewEmployee(newEmployee);
		
		System.out.println("****************************************");
		
		List<Employee> updatedEmployees=service.getAllEmployees();
		updatedEmployees.forEach(x-> System.out.println("id: " + x.getId() + " firstname: " + x.getFirstName() + " salary: " + x.getSalary()));
	}

}
