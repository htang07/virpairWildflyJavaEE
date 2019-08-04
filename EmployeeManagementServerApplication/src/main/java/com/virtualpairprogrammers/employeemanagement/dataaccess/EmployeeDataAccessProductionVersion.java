package com.virtualpairprogrammers.employeemanagement.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
@Default
@ProductionDao
public class EmployeeDataAccessProductionVersion implements EmployeeDataAccess {

	@Override
	public void insert(Employee newEmployee) {
		
	}

	@Override
	public List<Employee> findAll() {
		Employee e1 = new Employee("Kelly","Blue","Author",1900);
		Employee e2 = new Employee("David","Brown","Cleaner",1600);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		return employees;
	}

	@Override
	public List<Employee> findBySurname(String surname) {
		return null;
	}

}
