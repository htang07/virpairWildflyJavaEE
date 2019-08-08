import java.util.List;

import generatedclasses.com.virtualpairprogrammers.employeemanagement.Employee;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.EmployeeManagementWebService;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.EmployeeManagementWebserviceImplementationService;
import generatedclasses.com.virtualpairprogrammers.employeemanagement.ServiceUnavailableException_Exception;

public class SoapWebServiceClientMain {

	public static void main(String[] args) throws ServiceUnavailableException_Exception {
		EmployeeManagementWebService service = new EmployeeManagementWebserviceImplementationService()
				.getEmployeeManagementWebServicePort();

		Employee newEmployee = new Employee();
		newEmployee.setFirstName("Tim");
		//newEmployee.setId(6);
		newEmployee.setJobRole("teacher");
		newEmployee.setSalary(2000);

		List<Employee> existingEmployees = service.getAllEmployees();
		existingEmployees.forEach(x -> System.out
				.println("id: " + x.getId() + " firstname: " + x.getFirstName() + " salary: " + x.getSalary()));

		service.registerNewEmployee(newEmployee);

		System.out.println("****************************************");

		List<Employee> updatedEmployees = service.getAllEmployees();
		updatedEmployees.forEach(x -> System.out
				.println("id: " + x.getId() + " firstname: " + x.getFirstName() + " salary: " + x.getSalary()));

	}

}
