package com.fittracker.fittracker.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.fittracker.fittracker.entity.User;

import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Company;

@Component("Services")

public interface Services<T> extends UserDetailsService {

	public T findById(int theId);

	// public String findNameById(int theId);

	public List<T> findAll();

	public UserDetails loadUserByUsername(String userName);

	public User findByUserName(String userName);

	public void save(T t);

	public void deleteById(int t);

	public void setCount2(int i, String name);

	public void allActivityTo0(int id);

	public int getActiveVisit(int id);

	public int getActiveVisitClub(int id, int clubId);

}
