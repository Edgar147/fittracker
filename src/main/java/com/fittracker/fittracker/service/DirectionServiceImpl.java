package com.fittracker.fittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.DirectionDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Service
@Component("dicSer")
public class DirectionServiceImpl implements Services {

	@Autowired
	private DirectionDAO direcDAO;
	
	@Override
	public void saveClient(Client theClient) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void saveDirection(Direction theDirection) {
		direcDAO.saveDirection(theDirection);
		
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




}
