package com.fittracker.fittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.service.ClientService;

@Controller
@RequestMapping("/fit")
public class ClientController {
	
	@Autowired
	private ClientService cliSer;
	
	
	
	@GetMapping("/registration")
	public String reg(@ModelAttribute("client") Client theClient) {

		return "saveClient";
	}
	
	@PostMapping("/save")
	public String saveClient(@ModelAttribute("client") Client theClient){

		cliSer.saveClient(theClient);
		
		return "redirect:/fit/registration";
	}

}
