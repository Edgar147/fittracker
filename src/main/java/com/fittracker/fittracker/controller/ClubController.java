package com.fittracker.fittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.service.Services;

@Controller
// @RequestMapping("/clubs")
public class ClubController {

	@Autowired
	@Qualifier("clubSer")
	private Services<Club> clubSer;
	
	@GetMapping("/clubtest")
	public String ClubTestPage(Model model) {
		Club club=clubSer.findById(1);
		model.addAttribute("club", club);
		
		return "clubTest";
	}

}
