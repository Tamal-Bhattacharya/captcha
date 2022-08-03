package com.bhattacharya.model.com.battacharya.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhattacharya.model.User;
import com.bhattacharya.model.com.battacharya.controller.com.bhattacharya.captcha.CaptchaGenerator;
import com.bhattacharya.model.com.battacharya.controller.com.bhattacharya.captcha.CaptchaUtils;

import nl.captcha.Captcha;


@RestController
public class RestEndpoint {
    private String message;

    @Autowired
    CaptchaGenerator captchaGenerator;
    
    @GetMapping("/")
    public String loginPage(Model model, HttpSession session){
        model.addAttribute("message", message);
		model.addAttribute("employee", new User());
		Captcha captcha = captchaGenerator.createCaptcha(200, 50);
		session.setAttribute("captcha", captcha.getAnswer());
		model.addAttribute("captchaEncode", CaptchaUtils.encodeBase64(captcha));
        return "login-page";
    }

    @PostMapping(value="/login")
    public String postMethodName(@ModelAttribute User user, HttpServletRequest request) {
        if(user.getUserName().equals("admin") && user.getPassword().equals("admin") && user.getCaptcha().equals(request.getSession().getAttribute("captcha"))){
            return "Logged In Successfully";
        }
        
        return "redirect:/";
    }
    
}
