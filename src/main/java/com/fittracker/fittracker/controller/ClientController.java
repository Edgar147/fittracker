package com.fittracker.fittracker.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Visit;
import com.fittracker.fittracker.service.Services;

@Controller
//@RequestMapping("/fit")
public class ClientController {
	

	
	@Autowired
	@Qualifier("cliSer")
	private Services<Client> cliSer;
	
	@Autowired
	@Qualifier("dicSer")
	private Services<Direction>  dicSer;
	
	
	@Autowired
	@Qualifier("clubSer")
	private Services<Club> clubSer;
	
	@Autowired
	@Qualifier("visSer")
	private Services<Visit> visSer;
	
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
	public String saveClient(@ModelAttribute("client") Client theClient,@Nullable @RequestParam("newPass") String value){
		
//		
//		theClient.addClub(theClub);
//		clubSer.saveClub(theClub);

		
//		Club te=clubSer.findById(1);
//		String te=clubSer.findNameById(2);
//
//		if (te!=null)
//		{
//			System.out.println(te);
//			System.out.println("ooooooooooooooooooKKKKKK");
//			System.out.println("ooooooooooooooooooKKKKKK");
//		}
//		else {
//			System.out.println(te);
//			System.out.println("fffffffffffff");
//			System.out.println("fffffffffffff");
//
//		}
//		
		
		
		
		int clid=theClient.getClubId();
		Club theClub=clubSer.findById(clid);
		ArrayList c = new ArrayList<>();
		c.add(theClub);
		theClient.setClubs(c);

		
		if(value != null) {
			theClient.setPassword(value);
		}
		
		

		
		
		cliSer.save(theClient);
		
		
		return "redirect:/registration/client";
	}
	
	
	
	@PostMapping("/savedirec")
	public String saveDirection(@ModelAttribute("direction") Direction theDirection){
		
		dicSer.save(theDirection);
		
		return "redirect:/registration/direction";
	}
	
	@PostMapping("/saveclub")
	public String saveClub(@ModelAttribute("club") Club theClub){
		
		clubSer.save(theClub);
		
		return "redirect:/registration/club";
	}

	
	@GetMapping("/list-clients")
	public String listEmployees(Model theModel) {
		
		
		List<Client> theClients=cliSer.findAll();

		
		
		
		//add to the spring model
		theModel.addAttribute("clients",theClients);
		
		return "list-clients";
	}
	
	
	@GetMapping("/list-direction")
	public String listDirection(Model theModel) {
		
		
		List<Direction> theDirections=dicSer.findAll();
		
		
		
		
		//add to the spring model
		theModel.addAttribute("direction",theDirections);
		
		return "list-direction";
	}
	
	
	@GetMapping("/myLogin")
	public String showMyLoginPage() {
		//Client x=cliSer.findById(5);
	
		return "login-page";
		
	}
	
	
	@GetMapping("/home")
	public String showMyHome(Model model,@ModelAttribute("client") Client theClient) {
	    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    

	     Client user = cd.findByClientName(login);

			List<Club> clubs=user.getClubs();

	    
		String role=user.getRole();
		int clientId=user.getId(); 
		int clubId= user.getClubId();
		
		Club club= clubSer.findById(clubId);

	    model.addAttribute("role", role);
	    model.addAttribute("clientId", clientId);
	    model.addAttribute("theClub", club.getName());
	    model.addAttribute("clubs", clubs);
		return "home";
		
	}
	
	
	@GetMapping("/addClub")
	public String addClub(Model theModel) {
		
		return "add-club";
	}
	
	@PostMapping("/addNewClub")
	public String addNewClub(@RequestParam("x") int value){
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);



	    
	    
	    
	    
		Club club=clubSer.findById(value);


		
ArrayList<Client> L= new ArrayList<>();
	L.add(client);
		club.setClients(L);
		clubSer.save(club);

		
		return "home";
	}
	
	
	@GetMapping("/updateClient")
	public String UpdateClient(@ModelAttribute("xxx") int theId, Model theModel)
	{
		//get the employee from the service
		Client theClient = cliSer.findById(theId);
		String thePassword=theClient.getPassword();
		
		//set employeee as a model attribute to pre-populate the form
		theModel.addAttribute("client", theClient);
		theModel.addAttribute("password", thePassword);
		
		//send over to our form
		
		return "client-update-form";
		
	}
	
	
	@GetMapping("/deleteClient")
	public String delete(@RequestParam("yyy") int theId) {
		cliSer.deleteById(theId);
		
		return "home";
		
		
	}
	
	
	@PostMapping("/presence")
	public String PresenceClient(Model model) {
		
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);
	    
	    int count=client.getCount();
	    
	    
	    if(count==1) {
	    	cliSer.setCount2(0,client.getFirstName());
			

	    }
	    else {
	    	cliSer.setCount2(1,client.getFirstName());
	    	
	    }
	    
		return "redirect:/home";
		
		
	}
	
	
	
	
	@PostMapping("/saveVisit")
	public String SaveVisit(@RequestParam("club_id") int club_id,Model model) {
		
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);
	    
	    
	    
	    int count=client.getCount();
	    LocalDateTime localDateTime = LocalDateTime.now();
	    visSer.allActivityTo0(client.getId());

	    Visit theVisit= new Visit(client.getId(),club_id,localDateTime,1);
	    
	    
	    
	    visSer.save(theVisit);
	    
	 
		return "redirect:/home";
		
		
	}
	
	@GetMapping("/list-visits")
	public String listVisits(@ModelAttribute("ggg") int club_id, Model theModel) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);
	    
		List<Visit> theVisits=client.getVisits();
		
		 List<Visit> myVisits = new ArrayList<>();

		for(int i=0;i<theVisits.size();i++) {
			if(theVisits.get(i).getClubId()==club_id) {
				myVisits.add(theVisits.get(i));
			}
		}
		
		Club club= clubSer.findById(club_id);
		String nameClub=club.getName();
		
		
		
		
		System.out.println("eeeee");
		System.out.println("eeeee");
		System.out.println("eeeee");
		System.out.println("eeeee");
		System.out.println(theVisits);
		//add to the spring model
		theModel.addAttribute("visits",myVisits);
		theModel.addAttribute("clubName",nameClub);
		
		return "list-visits";
	}
	
	
	
	
	
	@GetMapping("/clientControll")
	public String ClientControllByManager(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);
	    List<Club> clubs=client.getClubs();
		
		 //List<Client> theClients = cliSer.findAll();
		 List<Client> myClients = new ArrayList<>();

	    
		for(int i=0;i<clubs.size();i++) {
//			if(clubs.get(i).getId()==theClients.get(i).getClubId()) {
//				myClients.add(myClients.get(i));
//			}
			myClients=clubs.get(i).getClients();
			
			myClients.addAll(myClients);
			
			
		}
	    	
		Set<Client> set = new HashSet<>(myClients);

	    	
		
		model.addAttribute("clients",set);

		return "client-controll";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/club-client-list")
	public String ClubClientListForManager(@ModelAttribute("ppp") int club_id, Model theModel) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String login = loggedInUser.getName(); 
	    Client client = cd.findByClientName(login);
	    
	    
	    
	    
	    Club club=clubSer.findById(club_id);
	    List<Client> clients=club.getClients();
	    

		
		
		

		theModel.addAttribute("clients",clients);
		theModel.addAttribute("clubName",club.getName());

		
		return "club-client-list";
	}
	
	
	
	
	
	
	
	
	
}
