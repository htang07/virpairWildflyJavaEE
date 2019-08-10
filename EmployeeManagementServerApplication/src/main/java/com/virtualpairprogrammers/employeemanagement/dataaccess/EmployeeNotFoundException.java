package com.virtualpairprogrammers.employeemanagement.dataaccess;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class EmployeeNotFoundException extends Exception {
	
	private Exception innerException;
	public EmployeeNotFoundException() { super();}
	
	public EmployeeNotFoundException(Exception innerException, StackTraceElement[] trace) {
		super("innerException - " + innerException.getClass().getName() + ": " + innerException.getMessage());
		super.setStackTrace(trace);
//		this.innerException = innerException;
//		this.innerException.setStackTrace(trace);
	}
	
//	public String getStackTraceAsString() {
//		return ExceptionUtils.getStackTrace(innerException);
//	}
}
