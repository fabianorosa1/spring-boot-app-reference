package com.sample.springboot.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sample.springboot.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
		
	@Test
	public void addCustomer() throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"firstName\": \"Jonh\"");
		json.append(",");
		json.append("\"lastName\": \"Fish\"");
		json.append("}");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json.toString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(201));
	}
	
	@Test
	public void updateCustomer() throws Exception {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"id\": 1");
		json.append(",");
		json.append("\"firstName\": \"Jonh1\"");
		json.append(",");
		json.append("\"lastName\": \"Fish1\"");
		json.append("}");

		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json.toString()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(202));
	}
	
	@Test
	public void deleteCustomerFail() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/customer/12345")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(404));
	}
	
	@Test
	public void getCustomerById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/1")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.firstName").exists())
			.andExpect(jsonPath("$.lastName").exists())
			.andDo(print())
			.andExpect(status().is(200));
	}
	
	@Test
	public void getAllCustomer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isNotEmpty())
			.andDo(print())
			.andExpect(status().is(200));
	}
}
