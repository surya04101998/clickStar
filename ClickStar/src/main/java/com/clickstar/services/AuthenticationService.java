package com.clickstar.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickstar.models.LoginFormBean;
import com.clickstar.models.UserAuthBean;
import com.clickstar.security.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AuthenticationService {

	@Autowired
	UserService userAuthService;
	
	public boolean authenticateRequest(LoginFormBean userCredentials,HttpSession session) {
	log.debug("userCredentials have been passed with values {}",userCredentials);
	String token= userAuthService.createtoken(new UserAuthBean(userCredentials.getUsername(), userCredentials.getPassword()));
	session.setAttribute("token", token);
	log.info("token generated {}",token);
	return (token==null)?false:true;
	
}
}
