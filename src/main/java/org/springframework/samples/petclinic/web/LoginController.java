package org.springframework.samples.petclinic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("/welcome")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/login")  
	public ModelAndView onLogin(ModelMap model, HttpServletRequest request, HttpServletResponse res) {
	    String name = request.getParameter("name");  
	    String password = request.getParameter("password");  
	    
		if (!loginService.isValid()) {
			model.addAttribute("error", "Invalid user name and password");
			return new ModelAndView("relogin", model);
			}
		String userName = loginService.retrieveName();
		model.addAttribute("name", "Welcome "+userName+"!");
		return new ModelAndView("greetings", model);
	}
}
