package com.virtualpairprogrammers.employeemanagement.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Local
public interface EmployeeDataAccess {

	public abstract void insert(Employee newEmployee);

	public abstract List<Employee> findAll();

	public abstract List<Employee> findBySurname(String surname);

}