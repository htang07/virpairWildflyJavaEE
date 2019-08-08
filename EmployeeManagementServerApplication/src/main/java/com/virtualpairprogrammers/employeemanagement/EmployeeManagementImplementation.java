package com.virtualpairprogrammers.employeemanagement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.virtualpairprogrammers.employeemanagement.dataaccess.EmployeeDataAccess;
import com.virtualpairprogrammers.employeemanagement.dataaccess.ProductionDao;
import com.virtualpairprogrammers.employeemanagement.dataaccess.TestingDao;
import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
//By default, all methods in an EJB are transactional not just persistence layer
public class EmployeeManagementImplementation implements
		EmployeeManagementService, EmployeeManagementServiceLocal {
	
	@Inject
	//@TestingDao
	@ProductionDao
	private EmployeeDataAccess dao;
	
	@Inject
	private ExternalPayrollSystem payrollSystem;
	
	@Resource
	private SessionContext ctx;
	
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
	
	
	
//	//following propogation rule marking method to support existing transaction created by caller stack top level in a chain 
//	//but not to create a new transaction (a transaction is not needed)
//	//marked method is ok to run as part of a parent method's transaction if exist won't be affected by it so it supports any existing
//	//transaction; it doesn't need to have a new transaction created if there isn't one already
	@TransactionAttribute(TransactionAttributeType.SUPPORTS) // if applied to registerEmployee method registry won't work because the mark removes commit
	public double dummyInMemoryMethod() {
		return 109.29;
	}
	/*
	 * Note: 
	 * Transactions are automatically rolled back for unchecked exceptions. transaction are not rolled back for checked exceptions
	 * TransactionAttributeType.REQUIRES_NEW: marked child method creates a brand new transaction
	 * TransactionAttributeType.NOT_SUPPORTED: mark method doesn't need transaction and suspend parent transaction when running marked child method
	 * and give you the ability to do database
	 * schema manipulation for example. And then resume transaction at end execution of marked child method
	 * TransactionAttributeType.MANDATORY: indicate child method needs a transaction but application server
	 * shouldn't create one one with a mehtod; it must inherit transaction from a parent method.
	 * Therefore, parent method must have created the transcation. if parent transcation not provided it throws a exception
	 * TransactionAttributeType.NEVER: throws a exception is parent method created a transaction open. will run if no transaction open for child method
	 * TransactionAttributeType.REQUIRED: default for EJB meaning that a transaction will created if it's the first item in the chain or it 
	 * there's on in its parent then it will use that parent's transaction
	 */

	@Override
	public void enrollEmployeeHandleRollback(Employee employee) throws ServiceUnavailableException {
		payrollSystem.enrollEmployee(employee);
		//commented out for using alternative approach which is to annotate rollback on Exception class (ServiceUnavailableException for ex)
//		try {
//			payrollSystem.enrollEmployee(employee);
//		} catch (Exception e) {
//			System.out.println("Something went wrong");
//			ctx.setRollbackOnly();
//			throw e;
//		}
	}

	@Override
	public Employee getById(int id) {
		return dao.findById(id);
	}

}
