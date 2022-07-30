package com.fittracker.fittracker.service;


import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
public interface Services {
	
	public void saveClient(Client theClient);
	
	public void saveDirection(Direction theDirection);
	
	public void saveClub(Club theClub);


}
