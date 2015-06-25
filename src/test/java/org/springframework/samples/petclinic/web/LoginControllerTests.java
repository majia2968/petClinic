package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hsqldb.rights.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.samples.petclinic.service.LoginService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;

public class LoginControllerTests {

	@InjectMocks
	private LoginController loginController;
	
	@Mock
	private LoginService loginService;
	
	@Mock
	private User user;

	private MockMvc mockMvc;

	@Before
	public void  setup() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

	}

	@Test
	public void testLogin() throws Exception{
		mockMvc.perform(get("/welcome"))
		          .andExpect(status().isOk())
	              .andExpect(view().name("login"));

	}
	
	@Test
	public void testOnLogin() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("name", "chris");
		request.setParameter("password", "chris");
		
		when(loginService.isValid()).thenReturn(true);
		
		mockMvc.perform(get("/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("greetings"));

	}
	
	@Test
	public void testOnLoginFailed() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("name", "chris");
		request.setParameter("password", "chris");
		
		when(loginService.isValid()).thenReturn(false);
		
		mockMvc.perform(get("/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("relogin"));

	}
}



