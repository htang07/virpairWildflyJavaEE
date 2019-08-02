package com.virtualpairprogrammers.employeemanagement.domain;

public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 300668374425845613L;
	
	private String firstName;
	private String surname;
	private String jobRole;
	private int salary;
	
	public Employee(String firstName, String surname, String jobRole, int salary) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.jobRole = jobRole;
		this.salary = salary;
	}
	
	public String toString() {
		return "Employee : " + this.firstName + " " +this.surname;
	}
	
}

