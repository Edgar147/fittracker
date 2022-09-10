package com.fittracker.fittracker.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.fittracker.fittracker.dao.UserDAO;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Visit;
import com.fittracker.fittracker.service.Services;

@Controller
// @RequestMapping("/fit")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private Services<User> userService;

	@Autowired
	@Qualifier("directionService")
	private Services<Direction> directionService;

	@Autowired
	@Qualifier("clubService")
	private Services<Club> clubService;

	@Autowired
	@Qualifier("visitService")
	private Services<Visit> visitService;

	@Autowired
	private UserDAO ud;

	@GetMapping("/")
	public String LoginPage() {

		return "login-page";
	}

	@GetMapping("/administration")
	public String AdminPage() {

		return "index";
	}

	@GetMapping("/registration/user")
	public String UserRegistration(@ModelAttribute("user") User theUser) {

		return "saveUser";
	}

	@GetMapping("/registration/direction")
	public String CompanyRegistration(@ModelAttribute("direction") Direction theDirection) {

		return "saveDirection";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser,
			@Nullable @RequestParam("newPassword") String value) {

		int user_id = theUser.getClubId();
		Club theClub = clubService.findById(user_id);
		ArrayList c = new ArrayList<>();
		c.add(theClub);
		theUser.setClubs(c);

		if (value != null) {
			theUser.setPassword(value);
		}

		userService.save(theUser);

		return "redirect:/registration/user";
	}

	@PostMapping("/savedirec")
	public String saveCompany(@ModelAttribute("direction") Direction theDirection) {

		directionService.save(theDirection);

		return "redirect:/registration/direction";
	}

	@GetMapping("/list-users")
	public String returnListOfEmployees(Model theModel) {

		List<User> theUsers = userService.findAll();

		// add to the spring model
		theModel.addAttribute("users", theUsers);

		return "list-users";
	}

	@GetMapping("/list-direction")
	public String returnListCompany(Model theModel) {

		List<Direction> theDirections = directionService.findAll();

		// add to the spring model
		theModel.addAttribute("direction", theDirections);

		return "list-direction";
	}

	@GetMapping("/home")
	public String showHomepage(Model model, @ModelAttribute("user") User theUser) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();

		User user = ud.findByUserName(login);

		List<Club> clubs = user.getClubs();

		String role = user.getRole();
		int userId = user.getId();
		int clubId = user.getClubId();

		Club club = clubService.findById(clubId);

		model.addAttribute("role", role);
		model.addAttribute("userId", userId);
		model.addAttribute("theClub", club.getName());
		model.addAttribute("clubs", clubs);
		return "home";

	}

	@GetMapping("/updateUser")
	public String UpdateUser(@ModelAttribute("user_id") int theId, Model theModel) {
		// get the employee from the service
		User theUser = userService.findById(theId);
		String thePassword = theUser.getPassword();

		// set employeee as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("password", thePassword);

		// send over to our form

		return "user-update-form";

	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("user_id_for_delete") int theId) {
		userService.deleteById(theId);

		return "home";

	}

	// Just TRY ME button trigger
	@PostMapping("/presence")
	public String PresenceUser(Model model) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);

		int count = user.getCount();

		if (count == 1) {
			userService.setCount2(0, user.getFirstName());

		} else {
			userService.setCount2(1, user.getFirstName());

		}

		return "redirect:/home";

	}

	@PostMapping("/saveVisit")
	public String SaveVisit(@RequestParam("club_id") int club_id, Model model) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);

		int count = user.getCount();
		LocalDateTime localDateTime = LocalDateTime.now();
		visitService.allActivityTo0(user.getId());

		Visit theVisit = new Visit(user.getId(), club_id, localDateTime, 1);

		visitService.save(theVisit);

		return "redirect:/home";

	}

	// |Show history| -> button trigger
	@GetMapping("/list-visits")
	public String listVisits(@ModelAttribute("club_id") int club_id, Model theModel) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);

		List<Visit> theVisits = user.getVisits();

		List<Visit> myVisits = new ArrayList<>();

		for (int i = 0; i < theVisits.size(); i++) {
			if (theVisits.get(i).getClubId() == club_id) {
				myVisits.add(theVisits.get(i));
			}
		}

		Club club = clubService.findById(club_id);
		String nameClub = club.getName();

		System.out.println(theVisits);
		// add to the spring model
		theModel.addAttribute("visits", myVisits);
		theModel.addAttribute("clubName", nameClub);

		return "list-visits";
	}

	// Manager controll page -> Get all users which are connected with manager clubs
	@GetMapping("/userControll")
	public String UserControllByManager(Model model) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);
		List<Club> clubs = user.getClubs();

		// List<User> theUsers = userService.findAll();
		List<User> myUsers = new ArrayList<>();

		for (int i = 0; i < clubs.size(); i++) {
			// if(clubs.get(i).getId()==theUsers.get(i).getClubId()) {
			// myUsers.add(myUsers.get(i));
			// }
			myUsers = clubs.get(i).getUsers();

			myUsers.addAll(myUsers);

		}

		Set<User> set = new HashSet<>(myUsers);

		model.addAttribute("users", set);

		return "user-controll";

	}

	// Manager controll active page -> all active users that belongs to manager
	@GetMapping("/userControll-actives")
	public String AllUserControllofActivsByManager(Model model) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);
		List<Club> clubs = user.getClubs();

		// List<User> theUsers = userService.findAll();
		List<User> myUsers = new ArrayList<>();

		for (int i = 0; i < clubs.size(); i++) {
			// if(clubs.get(i).getId()==theUsers.get(i).getClubId()) {
			// myUsers.add(myUsers.get(i));
			// }

			myUsers = clubs.get(i).getUsers();

			myUsers.addAll(myUsers);

		}

		List<User> myActiveUsers = new ArrayList<>();

		for (int i = 0; i < myUsers.size(); i++) {
			if (visitService.getActiveVisit(myUsers.get(i).getId()) == 1) {
				myActiveUsers.add(myUsers.get(i));
			}
		}

		Set<User> setActive = new HashSet<>(myActiveUsers);

		model.addAttribute("users", setActive);

		return "user-controll-activs";

	}

	// Show users of club -> Show to manager the list of users of given club
	@GetMapping("/club-user-list")
	public String ClubUserListForManager(@ModelAttribute("club_id") int club_id, Model theModel) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);

		Club club = clubService.findById(club_id);
		List<User> users = club.getUsers();

		theModel.addAttribute("users", users);
		theModel.addAttribute("clubName", club.getName());

		return "club-user-list";
	}

	// Show active users of club -> show active users whp are in given club
	@GetMapping("/club-user-list-actives")
	public String ClubUserActiveListForManager(@ModelAttribute("club_id") int club_id, Model theModel) {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String login = loggedInUser.getName();
		User user = ud.findByUserName(login);
		List<Club> clubs = user.getClubs();

		// List<User> theUsers = userService.findAll();
		List<User> myUsers = new ArrayList<>();

		for (int i = 0; i < clubs.size(); i++) {
			// if(clubs.get(i).getId()==theUsers.get(i).getClubId()) {
			// myUsers.add(myUsers.get(i));
			// }

			myUsers = clubs.get(i).getUsers();

			myUsers.addAll(myUsers);

		}

		List<User> myActiveUsers = new ArrayList<>();

		for (int i = 0; i < myUsers.size(); i++) {
			if (visitService.getActiveVisitClub(myUsers.get(i).getId(), club_id) == 1) {
				myActiveUsers.add(myUsers.get(i));
			}
		}

		Set<User> setActive = new HashSet<>(myActiveUsers);

		theModel.addAttribute("users", setActive);

		return "club-user-list-actives";
	}

	@GetMapping("/user-profile")
	public String UserProfile(@ModelAttribute("user_id") int user_id, Model theModel) {

		User theUser = userService.findById(user_id);

		theModel.addAttribute("user", theUser);

		return "user-profile";
	}

}
