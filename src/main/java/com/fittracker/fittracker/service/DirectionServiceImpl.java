package com.fittracker.fittracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.DirectionDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Service
@Component("dicSer")
public class DirectionServiceImpl implements Services<Direction> {

	@Autowired
	private DirectionDAO direcDAO;
	

	@Override
	public Direction findById(int theId) {

		return null;
	}



	@Override
	public List<Direction> findAll() {
		return direcDAO.findAll();
	}


	@Override
	public UserDetails loadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findByClientName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Direction theDirection) {
		direcDAO.saveDirection(theDirection);
		
	}



	@Override
	public void deleteById(int t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setCount2(int i, String name) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void allActivityTo0(int id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int getActiveVisit(int id) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getActiveVisitClub(int id, int clubId) {
		// TODO Auto-generated method stub
		return 0;
	}




}
