package com.almas.assignment.demo.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almas.assignment.demo.model.Demo;
import com.almas.assignment.demo.repository.DemoJdbcRepository;

@Controller
public class MainController {
	
	private final String REDIRECT_PREFIX = "P"; 
	private final String LOCALHOST_PATH = "http://localhost:8080/"; 

	@Autowired
	DemoJdbcRepository demoJdbcRepository;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/")
	public String index() {
		return "redirect:welcome";
	}

	@GetMapping("/" + REDIRECT_PREFIX + "{page}")
	public String index(@PathVariable("page") String page, Model model) {
		Demo dd = demoJdbcRepository.findByShortUrl(LOCALHOST_PATH + REDIRECT_PREFIX + page);
		model.addAttribute("page", dd.getFullUrl());
		return "redirect";
	}

	@GetMapping("/list")
	public String login(Model model) {
		model.addAttribute("demos", demoJdbcRepository.findAll());
		return "list";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam(value = "fullUrl", required = true) String fullUrl,
			@RequestParam(value = "description", required = false) String description, Model model) {
		
		String shortUrl = LOCALHOST_PATH + REDIRECT_PREFIX + RandomStringUtils.random(7, true, true);
		
		demoJdbcRepository.insert(new Demo(fullUrl, shortUrl, description));

		model.addAttribute("shortUrl", shortUrl);
		model.addAttribute("fullUrl", fullUrl);

		return "result";
	}
}
