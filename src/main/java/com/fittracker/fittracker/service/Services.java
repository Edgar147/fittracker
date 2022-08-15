package com.fittracker.fittracker.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.fittracker.fittracker.entity.Client;

import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Component("Services")

public interface Services<T>  extends UserDetailsService{
		

	public Club findById(int theId);
	
	public Client findClientById(int theId);
	
	public String findNameById(int theId);
	
	public List<Client> findAll();

	public UserDetails loadUserByUsername(String userName);

	public Client findByClientName(String userName);
	
	public void save(T t);

}
