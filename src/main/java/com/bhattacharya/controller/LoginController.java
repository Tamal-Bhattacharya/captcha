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
import com.bhattacharya.modal.User;

import nl.captcha.Captcha;

@Controller
public class LoginController {
	private String message;
	@Autowired
	private CaptchaGenerator captchaGenerator;

	Model model;
	
	@GetMapping("/")
	public String add(Model model, HttpSession httpSession) {
		this.model = model;
		model.addAttribute("message", message);
		model.addAttribute("user", new User());
		Captcha captcha = captchaGenerator.createCaptcha(200, 50);
		httpSession.setAttribute("captcha", captcha.getAnswer());
		model.addAttribute("captchaEncode", CaptchaUtils.encodeBase64(captcha));
		return "login";
	}
	
	@PostMapping("/login")
	public String save(@ModelAttribute("user") User employee, HttpServletRequest request) {
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

	@GetMapping("/reset")
	public String reset() {
		return "redirect:/";
	}
}
