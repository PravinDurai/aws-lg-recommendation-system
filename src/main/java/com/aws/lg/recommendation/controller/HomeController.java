package com.aws.lg.recommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.lg.recommendation.dto.ECInstanceDto;
import com.aws.lg.recommendation.model.ECInstanceModel;
import com.aws.lg.recommendation.model.InstanceModel;
import com.aws.lg.recommendation.service.HomeService;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping("/display")
	public String loadHome(Model model) {
		model.addAttribute("InstanceModel", new InstanceModel(homeService.getAllRegions(),homeService.getAllScriptComplexity()));
		return("home");
	}
	
	@PostMapping("/calculate")
	public String calculateOhioEast(@ModelAttribute("InstanceModel") InstanceModel instanceModel) {
		System.out.println(instanceModel.toString());
		return("redirect:/home/display");
	}
	
	@PostMapping("/logout")
	public String logout() {
		return("redirect:https://forms.gle/3t4rZdTrfCBPAf899");
	}

}
