package org.springframework.samples.petclinic.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTests {

	@InjectMocks
	private LoginController loginController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

	}

	@Test
	public void testLogin() throws Exception{
		mockMvc.perform(get("/welcome"))
		          .andExpect(status().isOk())
	              .andExpect(view().name("login"));

	}
}
