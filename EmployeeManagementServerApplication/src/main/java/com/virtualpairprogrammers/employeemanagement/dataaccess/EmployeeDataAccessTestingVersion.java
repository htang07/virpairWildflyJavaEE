package com.virtualpairprogrammers.employeemanagement.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

//can remove @stateless annotation to remove its ejb mark and can still be injected correctly
//@Stateless
@Alternative
@TestingDao
//@SuppressWarnings("serial")
@RequestScoped
public class EmployeeDataAccessTestingVersion implements EmployeeDataAccess {

	@Override
	public void insert(Employee newEmployee) {
		
	}

	@Override
	public List<Employee> findAll() {
		Employee e1 = new Employee("James","Green","Writer",1700);
		Employee e2 = new Employee("Sharon","White","Editor",2100);
		Employee e3 = new Employee("Darren","Blue","Manager",31250);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		return employees;
	}

	@Override
	public List<Employee> findBySurname(String surname) {
		return null;
	}

	@Override
	public Employee findById(int id) {
		
		return null;
	}

}
