package com.hieu.springmvc.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hieu.springmvc.model.MyUser;
import com.hieu.springmvc.service.UserService;

@Controller
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView showLogInPage(@RequestParam(value="signUpSuccessful", required=false) String signUpSuccessful,
									@RequestParam(value="error", required=false) String error,
									@RequestParam(value="logout", required=false) String logout) {
		ModelAndView model = new ModelAndView();
		if(signUpSuccessful != null) {
			model.addObject("signUpSuccessful", "Sign up successful! Please log in to continue.");
		}
		
		if(error != null) {
			model.addObject("error", "Invalid credentials.");
		}
		
		if(logout != null) {
			model.addObject("logout", "You have been logged out successfully!");
		}
		
		model.setViewName("login");
		return model;
	}
	
	/*@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView logIn(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
		if(!userService.authenticateLogIn(username, password)) {
			
			return new ModelAndView("login","errorMessage", "Invalid credentials");
		}
		user = userService.findUserByUsername(username);
		request.getSession().setAttribute("user", user);
		return new ModelAndView(new RedirectView("welcome"),"user",user);
	}*/
	
	@RequestMapping(value= {"/","/home"})
	public String showWelcomePage(HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			request.getSession().setAttribute("user", user);
		}
		return "home";
	}
	
	
	@RequestMapping(value="/signup")
	public String showSignUpPage() {
		return "signup";
	}
	
	@RequestMapping(value="/auth-signup")
	public ModelAndView signUp(@RequestParam String username, @RequestParam String password,
						@RequestParam String firstName, @RequestParam String lastName) {
		if(!userService.authenticateSignUp(username)) {
			
			return new ModelAndView("signup", "invalidUsername", "Username already exists. Please choose another one.");
		}
		
		userService.createUser(username, password, firstName, lastName);
		MyUser user = new MyUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUsername(username);
		
		return new ModelAndView("redirect:login?signUpSuccessful");
		
	}
	
	@RequestMapping(value="/about")
	public String showPageAbout() {
		return "about";
	}
	
	@RequestMapping(value="/403")
	public String showPage403() {
		return "403";
	}

}
