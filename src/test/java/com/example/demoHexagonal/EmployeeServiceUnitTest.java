package com.example.demoHexagonal;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demoHexagonal.domain.Employee;
import com.example.demoHexagonal.exception.NotFoundException;
import com.example.demoHexagonal.port.EmployeeRepositoryPort;
import com.example.demoHexagonal.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceUnitTest {
	
	
	
	@Mock
	private EmployeeRepositoryPort employeeRepository;
	
	// class to test, we inject mock in this class if @bean exist (for example :EmployeeRepositoryPort employeeRepository) 
	@InjectMocks
	private EmployeeService employeeServiceToTest; 

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test(expected=NotFoundException.class)
	public void WhenEmployeeNotFound_RaiseExceptionTest() throws NotFoundException {

		Mockito.when(employeeRepository.getEmployee(Mockito.anyInt())).thenReturn(null);
		
		
		employeeServiceToTest.view(1);
		
		
	}

	@Test
	public void WhenEmployeeFound_ReturenEmployeeTest() throws NotFoundException {

		Employee employeeToTest = new Employee();
		employeeToTest.setName("Albert");
		employeeToTest.setRole("Manager");
		employeeToTest.setSalary(15000L);
		
		Mockito.when(employeeRepository.getEmployee(Mockito.anyInt())).thenReturn(employeeToTest);
		
		Employee e = employeeServiceToTest.view(1);
		assertEquals("Name is not correct", "Albert", e.getName());
		
		
	}


}
