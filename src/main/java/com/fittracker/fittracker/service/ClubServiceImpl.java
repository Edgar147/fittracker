package com.fittracker.fittracker.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClubDAO;
import com.fittracker.fittracker.dao.ClubRepository;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Service
@Component("clubSer")
public class ClubServiceImpl implements Services {

	 @Autowired
	 EntityManager entityManager;
	
	
	@Autowired
	private ClubDAO clubDAO;
	
	private ClubRepository clubRepository;
	public ClubServiceImpl( ClubRepository theClubRepository) {
		clubRepository=theClubRepository;
	}
	
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

	

	@Override
	public Club findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Club> result = clubRepository.findById(theId);
		Club theClub=null;
		if(result.isPresent()) {
			theClub=result.get();
		}
		else {
			//we didn't find the employee
			
			throw new RuntimeException("Did not find Club id -"+ theId);
		}
		return theClub;
	}

	@Override
	public String findNameById(int theId) {
        
        
		Query theQuery =entityManager.createQuery("select name from Club  where id =:clubId");
		theQuery.setParameter("clubId", theId);
		List<Club> theClub=theQuery.getResultList();
        
        
        
		return theClub.toString();
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
