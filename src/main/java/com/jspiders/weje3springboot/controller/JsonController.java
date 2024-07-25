package com.jspiders.weje3springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.jspiders.weje3springboot.service.JsonService;

@RestController
public class JsonController {
	
	@Autowired
	private JsonService service;
	 
	@PostMapping("/addJson")
	public String manipulationJson(@RequestBody Map<String, List<Map<String,String>>> input)throws Exception {
		List<Map<String,String>> replacements=input.get("replacements");
		if(replacements == null) {
		return "Replacements cannot be null..!!";
		  }
		return service.manipulateJson(replacements);
		
	}

}
