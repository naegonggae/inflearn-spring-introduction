package com.naver.inflearnspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	// GetMapping 우선순위가 있음
	// 요청이 들어오면 먼저 컨테이너를 뒤지고 없으면 static 파일을 뒤진다.
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
