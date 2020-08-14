package com.clickstar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TaskController {
	@GetMapping("/taskList")
	public String showTaskPage(HttpSession session,Model m) {
		log.debug("token ={}",session.getAttribute("token"));
		m.addAttribute("ytlink", "https://www.youtube.com/");
		return "taskPage";
	}
	
	@GetMapping("/preFilter")
	public String processAndHandle(HttpSession session) {
		log.debug("Stub to add this user to list of task completion");
		return "redirect:https://gplinks.co/IU0Z";
	}
	
}
