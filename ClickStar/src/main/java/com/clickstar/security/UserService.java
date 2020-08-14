package com.clickstar.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clickstar.models.UserAuthBean;
import com.clickstar.repositories.UserBeanRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserBeanRepo userRepo;
	
	@Autowired
	private JwtUtil jwtUtilServ;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuthBean user=userRepo.findByUsername(username);
		
		return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
			

}
	
	public boolean validateToken(String token) {

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			String username = jwtUtilServ.extractUserName(token);
			UserDetails userDetails =loadUserByUsername(username);
			boolean flag = jwtUtilServ.validateToken(token, userDetails);
			if (flag) {
				return true;
			} else
				return false;
		}
		return false;
	}

	public String createtoken(UserAuthBean user) {
		if (user != null && user.getPassword() != null && user.getUsername() != null) {
			
			UserAuthBean u = userRepo.findByUsernameAndPassword(user.getUsername().trim(), user.getPassword().trim());
			if (u != null) {
				return jwtUtilServ.generateToken(loadUserByUsername(u.getUsername()));
			}
		}
		return null;
	}
	
	
	public UserAuthBean produceUser(String token) {
		if(validateToken("Bearer "+	token)){
			String username= jwtUtilServ.extractUserName(token);
			log.debug(username);
			return userRepo.findByUsername(username);
		}
		else
			return new UserAuthBean("invalid user", "invalid user");
	}
	
	
	public UserAuthBean addUser(UserAuthBean newUser) {
		return userRepo.save(newUser);
	}
	
}
