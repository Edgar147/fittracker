package com.fittracker.fittracker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fittracker.fittracker.dao.UserDAO;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.service.Services;

@Controller
// @RequestMapping("/clubs")
public class ClubController {

	@Autowired
	@Qualifier("clubService")
	private Services<Club> clubService;

	@Autowired
	private UserDAO cd;
	
	
	@GetMapping("/clubtest")
	public String ClubTestPage(Model model) {
		Club club = clubService.findById(1);
		model.addAttribute("club", club);

		return "clubTest";
	}
	
	
	@GetMapping("/registration/club")
	public String reg3(@ModelAttribute("club") Club theClub) {

		return "saveClub";
	}
	
	
	@PostMapping("/saveclub")
	public String saveClub(@ModelAttribute("club") Club theClub) {

		clubService.save(theClub);

		return "redirect:/registration/club";
	}
	
	
	
	
	@GetMapping("/addClub")
	public String addClub(Model theModel) {

		return "add-club";
	}

	@PostMapping("/addNewClub")
	public String addNewClub(@RequestParam("new_club_id") int value) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = cd.findByUserName(login);

		Club club = clubService.findById(value);

		ArrayList<User> L = new ArrayList<>();
		L.add(user);
		club.setUsers(L);
		clubService.save(club);

		return "redirect:/home";
	}

}
