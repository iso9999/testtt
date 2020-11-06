package com.ensa.pfa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	
	@RequestMapping("/ADMIN/page")
	public String page() {
		return "/ADMIN/page";
	}
	
	@RequestMapping("/ADMIN/home")
	public String home() {
		return "/ADMIN/home";
	}
}
