package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	@Transactional
	public void saveEmployee(Employee e) {
	    repository.save(e);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee e) {
       repository.save(e);
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer id) {
        repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Employee getEmployeeById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

}//EmployeeServiceImpl class
