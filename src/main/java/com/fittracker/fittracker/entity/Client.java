package com.fittracker.fittracker.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	
	@ManyToMany
	@JoinTable(
		name="club_client",
		joinColumns=@JoinColumn(name="client_id"),
		inverseJoinColumns=@JoinColumn(name="club_id")
			)
	private List<Club> clubs;

	
	
	//THE SAME BUT FOR VISITS
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "clients_roles", 
	joinColumns = @JoinColumn(name = "client_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "visit", 
	joinColumns = @JoinColumn(name = "client_id"), 
	inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Visit> visits;
	
	
	
	
	public List<Visit> getVisits() {
		return visits;
	}


	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="club_id")
	private int clubId;
	
	

	@Column(name = "password")
	private String password;
	
	
	@Column(name = "role")
	private String role;

	@Column(name="count")
	private int count;
	
	
	
	
	
	
	
	

	
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		
		
		this.count = count;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Client(List<Club> clubs, Collection<Role> roles, int id, String firstName, String lastName, String email,
			int clubId, String password, String role) {
		super();
		this.clubs = clubs;
		this.roles = roles;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.clubId = clubId;
		this.password = password;
		this.role = role;
	}


	public Client() {
		
	}
	
	
	public Client( int id, String firstName, String lastName, String email,int clubId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.clubId = clubId;
	}


	public Client( String firstName, String lastName, String email,int clubId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.clubId = clubId;
	}

	

	public Client(List<Club> clubs, Collection<Role> roles, int id, String firstName, String lastName, String email,
			int clubId, String password) {
		this.clubs = clubs;
		this.roles = roles;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.clubId = clubId;
		this.password = password;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public String getPassword() {

		return password;
	}


	public void setPassword(String password) {


		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}


	public List<Club> getClubs() {
		return clubs;
	}


	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	
	public void addClub(Club theClub) {
		
		if (clubs == null) {
			clubs = new ArrayList<>();
		}
		
		clubs.add(theClub);
	}


	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}



	
	
	

}
