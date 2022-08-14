package com.fittracker.fittracker.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fittracker.fittracker.dao.ClientDAO;
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
	
	@Autowired
	private ClientDAO cd;
	//private List<Client> theClients;

	
	@GetMapping("/")
	public String FirstPage() {

		return "login-page";
	}
	
	
	@GetMapping("/administration")
	public String AdminPage() {

		return "index";
	}
	
	
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
		
//		
//		theClient.addClub(theClub);
//		clubSer.saveClub(theClub);

		
//		Club te=clubSer.findById(1);
		String te=clubSer.findNameById(2);

		if (te!=null)
		{
			System.out.println(te);
			System.out.println("ooooooooooooooooooKKKKKK");
			System.out.println("ooooooooooooooooooKKKKKK");
		}
		else {
			System.out.println(te);
			System.out.println("fffffffffffff");
			System.out.println("fffffffffffff");

		}
		
		

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

	
	@GetMapping("/list-clients")
	public String listEmployees(Model theModel) {
		
		
		List<Client> theClients=cliSer.findAll();

		
		
		
		//add to the spring model
		theModel.addAttribute("clients",theClients);
		
		return "list-clients";
	}
	
	
	@GetMapping("/myLogin")
	public String showMyLoginPage() {
		
		return "login-page";
		
	}
	
	
	@GetMapping("/home")
	public String showMyHome(Model model,@ModelAttribute("client") Client theClient) {
	    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String aaa = loggedInUser.getName(); 
	    

	     Client user = cd.findByClientName("tufta");

	    
		String role=user.getRole();
		int clientId=user.getId(); 
		System.out.println("ddddddddddddddd");
		System.out.println(role);
		System.out.println("ddddddddddddddd");
	    model.addAttribute("role", role);
	    model.addAttribute("clientId", clientId);
		return "home";
		
	}
	
	
	
}
