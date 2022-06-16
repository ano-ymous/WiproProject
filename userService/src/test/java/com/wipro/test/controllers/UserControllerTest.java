package com.wipro.test.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.controllers.UserController;
import com.wipro.model.UserCredentialModel;
import com.wipro.model.Users;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
	private Users user1;
	private UserCredentialModel cred1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		user1 = new Users("tharun","tharun kumar reddy","kunduru","tharun.kunduru@gmail.com",9494090070L,new Date("2001-03-14"),"male","in");
		cred1 = new UserCredentialModel(
				"tharun.kunduru@gmail.com","UserCred@123");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
//		fail("Not yet implemented");
	}


}
