package com.fittracker.fittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClubDAO;
import com.fittracker.fittracker.dao.DirectionDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Service
@Component("clubSer")
public class ClubServiceImpl implements Services {

	@Autowired
	private ClubDAO clubDAO;
	
	@Override
	public void saveClient(Client theClient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveDirection(Direction theDirection) {

		
	}
	@Override
	@Transactional
	public void saveClub(Club theClub) {
		clubDAO.saveClub(theClub);
		
	}



}
