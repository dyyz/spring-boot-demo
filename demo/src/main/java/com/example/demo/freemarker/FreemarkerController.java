package com.example.demo.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fm")
public class FreemarkerController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("name", "Joe");
		model.addAttribute("sex", "female");
		
		return "/index";
	}
}
