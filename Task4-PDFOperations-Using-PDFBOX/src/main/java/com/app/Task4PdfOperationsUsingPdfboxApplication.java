package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.model.Employee;
import com.app.service.EmployeeService;

@SpringBootApplication
@EnableJpaRepositories
public class Task4PdfOperationsUsingPdfboxApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx=null;
		EmployeeService service=null;
		
		ctx=SpringApplication.run(Task4PdfOperationsUsingPdfboxApplication.class, args);

		service=ctx.getBean("empService", EmployeeService.class);
		
		
		Employee emp=service.getEmployeeById(1);
		System.out.println("Received EMp is:"+emp);
	}//main

}//class
