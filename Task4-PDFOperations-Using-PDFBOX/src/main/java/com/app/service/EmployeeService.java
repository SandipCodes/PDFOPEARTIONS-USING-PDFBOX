package com.app.service;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.app.model.Employee;

public interface EmployeeService {

	public void saveEmployee(Employee e);
	public void updateEmployee(Employee e);
	public void deleteEmployee(Integer id);
	public Employee getEmployeeById(Integer id);
	
	public List<Employee> getAllEmployees();
	
	public void savePdfAsImage(PDDocument document);
	public void mergeTwoPdfs();
	public void splitPdf();
	
	public void addRectangle();
	
	public void encryptPdf();
}
