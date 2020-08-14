package com.clickstar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clickstar.models.LoginFormBean;
import com.clickstar.services.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PortalController {
	
	@Autowired
	AuthenticationService userAuthService;
	
	
	public PortalController() {
		super();
		log.debug("PortalController object initialised");
	}

	@GetMapping("/")
	public String homePageLoader(@ModelAttribute("loginStatus")String loginStatus,Model m) {
		log.debug("homepage mapped by controller");
		log.debug("loginStatus ={}",loginStatus);
		return "homepage";
	}		
	
	@PostMapping("/login")
	public String loginPageLoader(@ModelAttribute("userCredentials") LoginFormBean userCredentials,Model m ) {
		log.debug("login page mapped by controller");
		return "login";
	}
	
	@PostMapping("/loginWithCredentials")
	public String credentialValidation(@ModelAttribute("userCredentials") LoginFormBean userCredentials,HttpSession session,Model m,RedirectAttributes redirectAttr) {
		if(userAuthService.authenticateRequest(userCredentials, session)) {
			log.info("Login successful");
			redirectAttr.addFlashAttribute("loginStatus","success");
			return "redirect:/taskList";
		}
		else
		{	
			log.info("Login failed");
			m.addAttribute("message","Invalid Credentials!!!");
			return "login";
		}
	}
	
	
}
