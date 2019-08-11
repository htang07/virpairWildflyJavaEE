import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestClientTest1 {

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
	public void testGetEmployeeById() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees/1").request("application/JSON")
				.buildGet().invoke();

		String result = response.readEntity(String.class); // Note: readEntity most of the time will close connection
															// except for stream inputstream for example
		System.out.println(result);// print out xml string
		response.close();
//		Employee employee = response.readEntity(Employee.class);
//		System.out.println(employee);
	}

	@Test
	public void testGetSingleEmployeeRestResource() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees/1").request("application/JSON")
				.buildGet().invoke();
		
		System.out.println("headers: \n" + response.getHeaders().toString());
		System.out.println("status: " + response.getStatus());
		Employee employee = response.readEntity(Employee.class);
		System.out.println(employee);
		response.close();
	}
	
	@Test
	public void testEmployeeRestResourceExpectingException() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees/44").request("application/JSON")
				.buildGet().invoke();
		
		System.out.println("headers: \n" + response.getHeaders().toString());
		System.out.println("status: " + response.getStatus());
		Employee employee = response.readEntity(Employee.class);
		System.out.println(employee);
		response.close();
	}

	@Test
	public void testPostEmployee() {
		Client client = ClientBuilder.newClient();

		Employee jimmy = new Employee();
		jimmy.setFirstName("Annie");
		jimmy.setSurname("Blue");
		jimmy.setJobRole("Reporter");
		jimmy.setSalary(10000);

		Entity jamesEntity = Entity.entity(jimmy, "application/XML");

		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees").request("application/XML")
				.buildPost(jamesEntity).invoke();
		
		System.out.println("headers: \n" + response.getHeaders().toString());
		System.out.println("status: " + response.getStatus());
		
		if(response.getStatus()==201)
			System.out.println(response.readEntity(Employee.class).toString());
		response.close();
	}

	@Test
	public void testGetEmployees() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees").request("application/XML")
				.buildGet().invoke();
		List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {
		});

		for (Employee e : employees) {
			System.out.println(e);
		}

		response.close();
	}

	@Test
	public void testGetEmployeesJson() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees")
				.request("application/JSON").buildGet().invoke();
		String result = response.readEntity(String.class); // Note: readEntity most of the time will close connection
		// except for stream inputstream for example
		System.out.println(result);// print out json string

		response.close();
		
	}
	
	@Test
	public void testGetEmployeesByRange() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees?firstId=1&secondId=4")
				.request("application/JSON").buildGet().invoke();
		String result = response.readEntity(String.class); // Note: readEntity most of the time will close connection
		// except for stream inputstream for example
		System.out.println(result);// print out json string

		response.close();
	}
	
	@Test
	public void testUpdatePutEmployee() {
		Client client = ClientBuilder.newClient();
		
		Employee updatedEmployee = new Employee();
		updatedEmployee.setJobRole("Producer");
		updatedEmployee.setSalary(1234);
		
		Entity eEnttity = Entity.entity(updatedEmployee,"application/JSON");
		
		Response response = client.target("http://localhost:8080/EmployeeManagement/webservice/employees/444").request().buildPut(eEnttity).invoke();

		System.out.println("Update status was " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		
		
		response = client.target("http://localhost:8080/EmployeeManagement/webservice/employees")
				.request("application/JSON").buildGet().invoke();
		List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {});
		
		for (Employee e : employees) {
			System.out.println(e + " "  + e.getJobRole() + " " + e.getSalary());
		}
	}
	
	@Test
	public void testDeleteEmployee() {
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees/203").request().buildDelete().invoke();
		System.out.println("Delete status was " + response.getStatus());
		
		response.close();
		
		response = client
				.target("http://localhost:8080/EmployeeManagementServerApplication/webservice/employees")
				.request("application/JSON").buildGet().invoke();
		String result = response.readEntity(String.class); // Note: readEntity most of the time will close connection
		// except for stream inputstream for example
		System.out.println(result);// print out json string
		
		response.close();
	}
	
	@Test
	public void testResponseMessageWithHeader() {
		
	}

}
