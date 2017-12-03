package com.lineng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello2Controller {

	
	@RequestMapping("/helloNew")
	public String helloNew(){
		return "helloNew";
	}
	
}
