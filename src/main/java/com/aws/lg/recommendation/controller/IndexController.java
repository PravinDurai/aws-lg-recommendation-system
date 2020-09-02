package com.aws.lg.recommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.lg.recommendation.model.LoginModel;

@Controller
@RequestMapping({"/","/index"})
public class IndexController {
	
	@GetMapping
	public String main(Model model) {
		model.addAttribute("Login",new LoginModel());
		System.out.println("index");
		return("redirect:/auth/login");
	}
}
