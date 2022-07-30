package com.fittracker.fittracker.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="club")
public class Club {
	
	
	@ManyToMany
	@JoinTable(
		name="club_client",
		joinColumns=@JoinColumn(name="club_id"),
		inverseJoinColumns=@JoinColumn(name="client_id")
			)
	private List<Client> clients;
	
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="coord")
	private String coord;
	
	@Column(name="scheldue")
	private String scheldue;

	public Club() {
		
	}

	public Club( String name, String address, String coord, String scheldue) {
		this.name = name;
		this.address = address;
		this.coord = coord;
		this.scheldue = scheldue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoord() {
		return coord;
	}

	public void setCoord(String coord) {
		this.coord = coord;
	}

	public String getScheldue() {
		return scheldue;
	}

	public void setScheldue(String scheldue) {
		this.scheldue = scheldue;
	}

	
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", address=" + address + ", coord=" + coord + ", scheldue="
				+ scheldue + "]";
	}
	

	

}
