package com.fittracker.fittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
@Service
@Component("cliSer")
public class ClientServiceImpl implements Services {

	@Autowired
	private ClientDAO clientDAO;
	
	
	@Override
	@Transactional
	public void saveClient(Client theClient) {
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



}
