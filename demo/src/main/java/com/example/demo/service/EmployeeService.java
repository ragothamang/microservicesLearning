package com.example.demo.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Employee;

@Service
public class EmployeeService {

	List<Employee> employeeList;
	public List<Employee> getAllEmployees(){
	Employee roger = Employee.builder().id(1).name("Roger").dob(LocalDate.now().minusYears(20)).build();
    Employee rafa = Employee.builder().id(2).name("Rafa").dob(LocalDate.now().minusYears(15)).build();
    return Arrays.asList(roger,rafa); 
	}
	
	public Employee getOneUSer(int id) {
		return null;
	}
}
