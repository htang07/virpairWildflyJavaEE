package com.virtualpairprogrammers.employeemanagement;

import javax.ejb.Stateless;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

@Stateless
public class ExternalPayrollSystem {

	public void enrollEmployee(Employee newEmployee) throws ServiceUnavailableException {
		throw new ServiceUnavailableException();
//		double test = Math.random();
//		if (test < 0.5) {
//			throw new ServiceUnavailableException();
//		}
//		else {
//			//do some complex processing
//		}
	}
}
