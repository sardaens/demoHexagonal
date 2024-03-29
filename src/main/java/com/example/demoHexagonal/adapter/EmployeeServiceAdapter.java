package com.example.demoHexagonal.adapter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demoHexagonal.domain.Employee;
import com.example.demoHexagonal.port.EmployeeRepositoryPort;

@Service
public class EmployeeServiceAdapter implements EmployeeRepositoryPort {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public void create(String name, String role, long salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);
        entityManager.persist(employee);
    }
    @Override
    public Employee getEmployee(Integer userId) {
        return entityManager.find(Employee.class, userId);
    }
}