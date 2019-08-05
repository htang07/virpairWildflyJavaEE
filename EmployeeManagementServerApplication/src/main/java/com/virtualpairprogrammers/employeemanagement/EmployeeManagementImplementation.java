package com.virtualpairprogrammers.employeemanagement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.virtualpairprogrammers.employeemanagement.dataaccess.EmployeeDataAccess;
import com.virtualpairprogrammers.employeemanagement.dataaccess.ProductionDao;
import com.virtualpairprogrammers.employeemanagement.dataaccess.TestingDao;
import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
public class EmployeeManagementImplementation implements
		EmployeeManagementService {
	
	@Inject
	//@TestingDao
	@ProductionDao
	private EmployeeDataAccess dao;
	
	public EmployeeManagementImplementation() {}
	
	@Override
	public void registerEmployee(Employee employee) {
		dao.insert(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return dao.findAll();
	}

	@Override
	public List<Employee> searchBySurname(String surname) {
		return dao.findBySurname(surname);
	}

}
