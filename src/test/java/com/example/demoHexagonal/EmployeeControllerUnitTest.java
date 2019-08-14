package com.example.demoHexagonal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demoHexagonal.domain.Employee;
import com.example.demoHexagonal.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class EmployeeControllerUnitTest {

	
	// Test de la partie Controller avec Mock (-> Unit test)
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private EmployeeService employeeService;
	
	
	@Test
	public void findEmployeeTest() throws Exception
	{
		Employee e = new Employee();
		e.setId(1);
		e.setName("alex");
		e.setRole("Manager");
		e.setSalary(150);
		
		Mockito.when(employeeService.view(Mockito.anyInt())).thenReturn(e);
		
		this.mockMvc.perform(get("/employees/view/1"))
			.andDo(print())
			.andExpect(status().is(200))
			.andExpect(content().json("{'id': 1,'name': 'alex';'role': 'Manager','salary':150}"));
	}
	
	
}
