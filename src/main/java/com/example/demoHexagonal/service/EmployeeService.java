package com.example.demoHexagonal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoHexagonal.domain.Employee;
import com.example.demoHexagonal.port.EmployeeRepositoryPort;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositoryPort employeeRepository;
	
	public void create(String name, String role, long salary){
        employeeRepository.create(name, role, salary);
    }
	
	public Employee view(Integer userId){
		return employeeRepository.getEmployee(userId);
	}
	
}
