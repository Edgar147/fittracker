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
		

	public T findById(int theId);
		
	//public String findNameById(int theId);
	
	public List<T> findAll();

	public UserDetails loadUserByUsername(String userName);

	public Client findByClientName(String userName);
	
	public void save(T t);
	
	public void deleteById(int t);

}
