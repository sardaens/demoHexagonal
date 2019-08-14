package com.example.demoHexagonal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demoHexagonal.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integration-test.properties")
public class EmployeeServiceIntegrationTest {

	@Autowired
    private MockMvc mockMvc;
 
 
	@Test
	public void givenEmployee_whenGetEmployee() throws Exception
	{
		
		String content = "{\n\t\"name\":\"toto\",\n\t\"role\":\"admin\",\n\t\"salary\":150\n}";
		this.mockMvc.perform(post("/employees/create")
				.contentType("application/json")
				.content(content))
			.andDo(print())
			.andExpect(status().is(200));
		
		
		
		// get employee created ?
		this.mockMvc.perform(get("/employees/view/1"))
		.andDo(print())
		.andExpect(status().is(200))
		.andExpect(content().json("{'id': 1,'name': 'toto';'role': 'admin','salary':150}"));
		
	}
	
	
	
}
