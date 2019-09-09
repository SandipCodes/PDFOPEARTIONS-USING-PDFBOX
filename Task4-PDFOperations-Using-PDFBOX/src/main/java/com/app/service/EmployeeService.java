package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {

	public void saveEmployee(Employee e);
	public void updateEmployee(Employee e);
	public void deleteEmployee(Integer id);
	public Employee getEmployeeById(Integer id);
	
	public List<Employee> getAllEmployees();
	
	
}
