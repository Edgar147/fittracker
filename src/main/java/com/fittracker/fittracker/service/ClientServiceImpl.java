package com.fittracker.fittracker.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.dao.ClientRepository;
import com.fittracker.fittracker.dao.RoleDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
@Service
@Component("cliSer")
public class ClientServiceImpl implements Services {

	@Autowired
	private ClientDAO clientDAO;
	
	
	@Autowired
	private RoleDAO roleDAO;
	
	private ClientRepository cl;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public ClientServiceImpl(ClientRepository cl) {
		this.cl = cl;
	}
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


	@Override
	@Transactional
	public void saveClient(Client theClient) {
		theClient.setPassword(passwordEncoder.encode(theClient.getPassword()));
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		System.out.println(theClient.getRole());
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");

//		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_MANAGER")));
		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName(theClient.getRole())));
		clientDAO.saveClient(theClient);
	}


	@Override
	public void saveDirection(Direction theDirection) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void saveClub(Club theClub) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Club findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String findNameById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Client> findAll() {
  
		
		return cl.findAll();
	}







}
