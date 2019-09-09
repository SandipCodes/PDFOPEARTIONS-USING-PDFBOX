package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Data;

@Entity
@Table(name="emp_tab")
@Proxy(lazy=false)
@Data
public class Employee {

	@Id
	@GeneratedValue
	private Integer eid;
	
	private String ename;
	private String city;
	private String erole;
	private Integer sal;
}
