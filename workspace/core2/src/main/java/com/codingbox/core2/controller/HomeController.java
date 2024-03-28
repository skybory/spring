package com.codingbox.core2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	// 기본 home 화면
	// localhost:9090/ -> home.html 호출
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
}
