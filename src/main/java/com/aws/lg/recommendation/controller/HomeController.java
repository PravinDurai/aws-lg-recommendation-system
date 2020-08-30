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
		OptimisedResult optimisedResult=homeService.calculate(instanceModel);
		int ttLinux[][]=new int[20][5];
		int ttWindows[][]=new int[20][5];
		if(optimisedResult.getSortedOrderLinux()==null||optimisedResult.getSortedOrderWindows()==null) {
			session.setAttribute("noData", false);
			session.setAttribute("message","Please select the mandatory fields and then try to find the optimised solution");
			session.setAttribute("showCharts", true);
		}else {
			session.setAttribute("noData", true);
			session.setAttribute("showCharts", false);
			session.setAttribute("graphRegion", instanceModel.getRegion());
			int i=0;
			for(Double temp:optimisedResult.getTopTOptResultLinux().keySet()) {
				for(int j=0;j<5;j++) {
					switch(j) {
					case 0:
						ttLinux[i][j]=optimisedResult.getTopTOptResultLinux().get(temp).getMicro();
						break;
					case 1:
						ttLinux[i][j]=optimisedResult.getTopTOptResultLinux().get(temp).getSmall();
						break;
					case 2:
						ttLinux[i][j]=optimisedResult.getTopTOptResultLinux().get(temp).getMedium();
						break;
					case 3:
						ttLinux[i][j]=optimisedResult.getTopTOptResultLinux().get(temp).getLarge();
						break;
					case 4:
						ttLinux[i][j]=optimisedResult.getTopTOptResultLinux().get(temp).getXtraLarge();
						break;
					}
				}
				i++;
			}
			for(Double temp:optimisedResult.getTopTOptResultWindows().keySet()) {
				//ttWindows[i++]=optimisedResult.getTopTOptResultWindows().get(temp).toArray();
			}
			session.setAttribute("TTLinux", ttLinux);
			session.setAttribute("TTWindows", ttWindows);
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
