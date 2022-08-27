package com.fittracker.fittracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.dao.DirectionDAO;
import com.fittracker.fittracker.dao.VisitDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Visit;
import com.fittracker.fittracker.repository.VisitRepository;

@Service
@Component("visSer")
public class VisitServiceImpl implements Services<Visit> {
	
	
	@Autowired
	private VisitDAO visitDao;
	
	
	private VisitRepository visitRepository;
	public VisitServiceImpl( VisitRepository theVisitRepository) {
		visitRepository=theVisitRepository;
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
	public void deleteById(int t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setCount2(int i, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Visit findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Visit theVisit) {
		visitRepository.save(theVisit);	
	}

	
	public void allActivityTo0(int clientId) {
		visitDao.setActiveClientToZero(clientId);
	}



}
