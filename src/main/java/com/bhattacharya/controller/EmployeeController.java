package com.bhattacharya.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhattacharya.captcha.CaptchaGenerator;
import com.bhattacharya.captcha.CaptchaUtils;
import com.bhattacharya.modal.Employee;

import nl.captcha.Captcha;

@Controller
public class EmployeeController {
	private String message;
	@Autowired
	private CaptchaGenerator captchaGenerator;

	Model model;
	
	@GetMapping("/")
	public String add(Model model, HttpSession httpSession) {
		this.model = model;
		model.addAttribute("message", message);
		model.addAttribute("employee", new Employee());
		Captcha captcha = captchaGenerator.createCaptcha(200, 50);
		httpSession.setAttribute("captcha", captcha.getAnswer());
		model.addAttribute("captchaEncode", CaptchaUtils.encodeBase64(captcha));
		return "add";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
		if(employee.getCaptcha().equals(request.getSession().getAttribute("captcha"))) {
			if (employee.getName().equals("admin") && employee.getPassword().equals("admin")) {
				return "redirect:/verified";
			}
			message = "Invalid Username/Password";
			return "redirect:/";
		} else {
			message = "Please verify captcha";
			return "redirect:/";
		}
	}

	@GetMapping("/verified")
	public String verified() {
		return "verified";
	}
}
