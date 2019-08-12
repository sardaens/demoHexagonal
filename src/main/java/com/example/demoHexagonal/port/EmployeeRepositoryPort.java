package com.example.demoHexagonal.port;

import com.example.demoHexagonal.domain.Employee;

public interface EmployeeRepositoryPort {

	void create(String name, String role, long salary);
	
	Employee getEmployee(Integer userId);
	
}
