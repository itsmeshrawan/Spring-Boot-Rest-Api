package com.neosoft.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.rest.model.User;
import com.neosoft.rest.repository.UserRepository;
import com.neosoft.rest.service.UserService;

@WebMvcTest
// @AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void positive_CreateUser() throws Exception {
		User user = new User();

		user.setFirstName("shrawan");
		user.setEmail("shrawan@gmail.com");
		user.setSecondaryEmail("abc@gmail.com");
		user.setPhone("7389248999");
		user.setSecondaryPhone("9754591157");
		user.setDob(new Date());
		user.setGender("Male");
		user.setBloodGroup("B+");
		user.setPanNumber("12345678");
		user.setAadharNumber("4535-65434");
		user.setAddressLine1("Village-memdi");
		user.setAddressLine2("Near Panchayat");
		user.setCity("Indore");
		user.setState("Madhya Pradesh");
		user.setPinCode("452020");
		user.setCountry("India");
		user.setCompanyName("NeoSoft Technologies");
		user.setDesignation("Senior Software Engineer");
		user.setLocation("Pune");
		user.setJoiningDate(new Date());
		user.setPassword("Secure@8999");
		user.setConfirmPassword("Secure@8999");

		Mockito.when(userService.save(user)).thenReturn(user);

		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void getAllUsers() throws Exception {
		List<User> users = new ArrayList<>();
		User user = new User();

		user.setFirstName("Bharat");
		user.setEmail("shrawan@gmail.com");
		user.setSecondaryEmail("abc@gmail.com");
		user.setPhone("7389248999");
		user.setSecondaryPhone("9754591157");
		user.setDob(new Date());
		user.setGender("Male");
		user.setBloodGroup("B+");
		user.setPanNumber("12345678");
		user.setAadharNumber("4535-65434");
		user.setAddressLine1("Village-memdi");
		user.setAddressLine2("Near Panchayat");
		user.setCity("Indore");
		user.setState("Madhya Pradesh");
		user.setPinCode("452020");
		user.setCountry("India");
		user.setCompanyName("NeoSoft Technologies");
		user.setDesignation("Senior Software Engineer");
		user.setLocation("Pune");
		user.setJoiningDate(new Date());
		user.setPassword("Secure@8999");
		user.setConfirmPassword("Secure@8999");

		users.add(user);

		Mockito.when(userService.getAllUsers("shrawan", "dob", "asc")).thenReturn(users);
		mockMvc.perform(get("/api/users")).andExpect(status().isOk());
	}

	@Test
	public void updateUser() throws Exception {
		User user = new User();

		user.setFirstName("Bharat");
		user.setEmail("shrawan@gmail.com");
		user.setSecondaryEmail("abc@gmail.com");
		user.setPhone("7389248999");
		user.setSecondaryPhone("9754591157");
		user.setDob(new Date());
		user.setGender("Male");
		user.setBloodGroup("B+");
		user.setPanNumber("12345678");
		user.setAadharNumber("4535-65434");
		user.setAddressLine1("Village-memdi");
		user.setAddressLine2("Near Panchayat");
		user.setCity("Indore");
		user.setState("Madhya Pradesh");
		user.setPinCode("452020");
		user.setCountry("India");
		user.setCompanyName("NeoSoft Technologies");
		user.setDesignation("Senior Software Engineer");
		user.setLocation("Pune");
		user.setJoiningDate(new Date());
		user.setPassword("Secure@8999");
		user.setConfirmPassword("Secure@8999");

		Mockito.when(userService.save(user)).thenReturn(user);
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(put("/api/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
