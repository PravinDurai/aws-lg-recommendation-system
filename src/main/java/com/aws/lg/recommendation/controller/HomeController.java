package com.aws.lg.recommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.lg.recommendation.model.ECInstanceModel;
import com.aws.lg.recommendation.model.InstanceModel;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	
	@GetMapping("/display")
	public String loadHome() {
		return("home");
	}
	
	@GetMapping("/calculate")
	public String calculateOhioEast(@ModelAttribute("InstanceModel") InstanceModel instanceModel,Model model) {
		ECInstance ecInstance=;
		model.addAttribute("ECInstance",ecInstance);
		return("redirect:/home/display");
	}
	
	@PostMapping("/logout")
	public String logout() {
		return("redirect:https://forms.gle/3t4rZdTrfCBPAf899");
	}

}
