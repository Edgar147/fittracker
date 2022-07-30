package com.fittracker.fittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.service.Services;

@Controller
//@RequestMapping("/fit")
public class ClientController {
	
	@Autowired
	@Qualifier("cliSer")
	private Services cliSer;
	
	@Autowired
	@Qualifier("dicSer")
	private Services dicSer;
	
	
	@Autowired
	@Qualifier("clubSer")
	private Services clubSer;
	

	
	
	@GetMapping("/registration/client")
	public String reg(@ModelAttribute("client") Client theClient) {

		return "saveClient";
	}
	
	
	@GetMapping("/registration/direction")
	public String reg2(@ModelAttribute("direction") Direction theDirection) {
		
		return "saveDirection";
	}
	
	
	@GetMapping("/registration/club")
	public String reg3(@ModelAttribute("club") Club theClub) {
		
		return "saveClub";
	}
	
	
	@PostMapping("/save")
	public String saveClient(@ModelAttribute("client") Client theClient){
		
		Club theClub=new Club("dsgdfs","dsg","dsg","dsg");
		
		theClient.addClub(theClub);
		clubSer.saveClub(theClub);

		cliSer.saveClient(theClient);
		
		
		return "redirect:/registration/client";
	}
	
	
	
	@PostMapping("/savedirec")
	public String saveDirection(@ModelAttribute("direction") Direction theDirection){
		
		dicSer.saveDirection(theDirection);
		
		return "redirect:/registration/direction";
	}
	
	@PostMapping("/saveclub")
	public String saveClub(@ModelAttribute("club") Club theClub){
		
		clubSer.saveClub(theClub);
		
		return "redirect:/registration/club";
	}

}
