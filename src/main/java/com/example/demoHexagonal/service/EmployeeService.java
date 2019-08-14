package com.example.demoHexagonal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoHexagonal.domain.Employee;
import com.example.demoHexagonal.exception.NotFoundException;
import com.example.demoHexagonal.port.EmployeeRepositoryPort;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositoryPort employeeRepository;
	
	public void create(String name, String role, long salary){
		
		// RG n1
		if (role == null || role.equals(""))
			employeeRepository.create(name, "developper", salary);
		else
			employeeRepository.create(name, role, salary);
    }
	
	public Employee view(Integer userId) throws NotFoundException{
		Employee e= employeeRepository.getEmployee(userId);
		if (e== null)
			throw new NotFoundException("Employee " + userId + " not found");
		return e;
	}
	
}
