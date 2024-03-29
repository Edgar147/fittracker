package com.fittracker.fittracker.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClubDAO;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Company;
import com.fittracker.fittracker.repository.ClubRepository;

@Service
@Component("clubService")
public class ClubServiceImpl implements Services<Club> {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private ClubDAO clubDAO;

	private ClubRepository clubRepository;

	public ClubServiceImpl(ClubRepository theClubRepository) {
		clubRepository = theClubRepository;
	}

	@Override
	public Club findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Club> result = clubRepository.findById(theId);
		Club theClub = null;
		if (result.isPresent()) {
			theClub = result.get();
		} else {
			// we didn't find the employee

			throw new RuntimeException("Did not find Club id -" + theId);
		}
		return theClub;
	}

	// IMPORTANT!!! For custom requests by hql
	/*
	 * @Override
	 * public String findNameById(int theId) {
	 * 
	 * 
	 * Query theQuery
	 * =entityManager.createQuery("select name from Club  where id =:clubId");
	 * theQuery.setParameter("clubId", theId);
	 * List<Club> theClub=theQuery.getResultList();
	 * 
	 * 
	 * 
	 * return theClub.toString();
	 * }
	 */

	@Override
	public List<Club> findAll() {
		return clubRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Club theClub) {
		clubRepository.save(theClub); // YOU CAN USE clubDAO.saveClub(theClub); as well

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
