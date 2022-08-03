package com.fittracker.fittracker.service;


import org.springframework.stereotype.Component;

import com.fittracker.fittracker.entity.Client;

import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Component("Services")

public interface Services {
	
	public void saveClient(Client theClient);
	
	public void saveDirection(Direction theDirection);
	
	public void saveClub(Club theClub);

	public Club findById(int theId);


}
