package com.aws.lg.recommendation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aws.lg.recommendation.algorithm.OptimisedResult;
import com.aws.lg.recommendation.model.InstanceModel;
import com.aws.lg.recommendation.model.LoginModel;
import com.aws.lg.recommendation.service.HomeService;
import com.aws.lg.recommendation.service.LoginService;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HomeService homeService;

	private int a;

	private HttpSession session;

	@GetMapping(value = "/login")
	public String loginGetMapping(@ModelAttribute("Login") LoginModel login, HttpServletRequest request, Model model) {
		session = request.getSession();
		a = 1;
		LoginModel loginModel = new LoginModel();
		session.setAttribute("Login", loginModel);
		session.setMaxInactiveInterval(2400);
		if (a == 1) {
			session.setAttribute("loginError", true);
			session.setAttribute("loginEMessage", "Please Enter a valid username/password...");
			a++;
		}
		return ("login");
	}

	@PostMapping("/login/validate")
	public String validateLogin(@ModelAttribute("Login") LoginModel login, Model model) {
		String firstName = loginService.validateLogin(login.geteMail(), login.getPassword());
		System.out.println("E-Mail :\t" + login.geteMail() + "\nPassword :\t" + login.getPassword()
				+ "\nValid Login :\t" + firstName);
		if (firstName != null) {
			OptimisedResult optimisedResult=new OptimisedResult();
			session.setAttribute("loginError", true);
			session.setAttribute("userName", "Hi " + firstName + "...");
			model.addAttribute("InstanceModel", new InstanceModel(homeService.getAllRegions(),homeService.getAllScriptComplexity()));
			session.setAttribute("noData", false);
			session.setAttribute("showCharts", true);
			session.setAttribute("LinuxMap",optimisedResult.getSortedOrderLinux() );
			session.setAttribute("WindowsMap",optimisedResult.getSortedOrderWindows() );
			return ("home");
		} else {
			model.addAttribute("Login", new LoginModel());
			session.setAttribute("loginEMessage", "Please Enter a valid username/password...");
			session.setAttribute("loginError", false);
			return ("redirect:/auth/login");
		}
	}
}
