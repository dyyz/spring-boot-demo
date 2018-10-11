package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping(value = {"/view"})
	public String view(Map<String, Object> map) {
		map.put("name", "SpringBoot");
		map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return "index";
	}
	
	@RequestMapping(value = "/test/produce/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String testProduces(@PathVariable String userId) {
		return "Hello " + userId;
	}
	
	@RequestMapping(value = "/test/consume", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String testConsum() {
		return "Hello Consume";
	}
	
	@ModelAttribute
	public void testModuleAttribute(@PathVariable(required = false) String userName, @RequestParam(name="age", required=false)Integer age, Model model) {
		model.addAttribute("user", userName);
//		model.addAttribute("age", age);
	}
	@RequestMapping("/testMA/{userName}")
	@ResponseBody
	public String testMA(@PathVariable String userName, @RequestParam(name="age", required=false)Integer age, Model model) {
		StringBuilder sb = new StringBuilder();
		if(model.containsAttribute("user")) {
			sb.append("model contain user, userName is " + userName);
			sb.append("\n");
		} else {
			sb.append("model dont contain user!!!");
			sb.append("\n");
		}
		if(model.containsAttribute("age")) {
			sb.append("model contain age, age is " + age);
			sb.append("\n");
		} else {
			sb.append("model dont contain age!!!");
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	@GetMapping("/testWC")
	@ResponseBody
	public Date testWC(@RequestParam(name = "date") Date date) {
//		
//		Date date = new Date();
		System.out.println(date);
		return date;
	}
}
