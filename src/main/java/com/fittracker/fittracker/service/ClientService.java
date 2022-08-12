package com.fittracker.fittracker.service;

import com.fittracker.fittracker.entity.Client;
//import com.luv2code.springsecurity.demo.entity.User;
//import com.luv2code.springsecurity.demo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface ClientService extends UserDetailsService {

	public Client findByClientName(String userName);

	public void save(Client crmUser);
}
