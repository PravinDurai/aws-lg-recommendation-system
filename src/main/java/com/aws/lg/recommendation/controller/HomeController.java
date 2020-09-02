package com.aws.lg.recommendation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.lg.recommendation.algorithm.ECInstance;
import com.aws.lg.recommendation.algorithm.OptimisedResult;
import com.aws.lg.recommendation.model.ECInstanceModel;
import com.aws.lg.recommendation.model.InstanceModel;
import com.aws.lg.recommendation.service.HomeService;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/display")
	public String loadHome(Model model) {
		model.addAttribute("InstanceModel", new InstanceModel(homeService.getAllRegions(),homeService.getAllScriptComplexity()));
		return("home");
	}
	
	@PostMapping("/calculate")
	public String calculateOhioEast(@ModelAttribute("InstanceModel") InstanceModel instanceModel) {
		OptimisedResult optimisedResult;
		if(instanceModel.getUserLoad()!=0) {
			optimisedResult=homeService.calculateUserLoad(instanceModel);
		}else {
			optimisedResult=homeService.calculateThroughput(instanceModel);
		}
		if(optimisedResult.getSortedOrderLinux()==null||optimisedResult.getSortedOrderWindows()==null) {
			session.setAttribute("noData", false);
			session.setAttribute("message","Please select the mandatory fields and then try to find the optimised solution");
			session.setAttribute("showCharts", true);
		}else {
			session.setAttribute("noData", true);
			session.setAttribute("showCharts", false);
			session.setAttribute("graphRegion", instanceModel.getRegion());
			int i=0;
			session.setAttribute("displayOptChart",true);
			session.setAttribute("optValuesLinux", optimisedResult.getOptLinux().toArray());
			session.setAttribute("optValuesWindows", optimisedResult.getOptWindows().toArray());
		}
		session.setAttribute("LinuxMap",optimisedResult.getTopTOptResultLinux() );
		session.setAttribute("WindowsMap",optimisedResult.getTopTOptResultWindows() );
		return("redirect:/home/display");
	}
	
	@PostMapping("/logout")
	public String logout() {
		return("redirect:https://forms.gle/3t4rZdTrfCBPAf899");
	}

}
