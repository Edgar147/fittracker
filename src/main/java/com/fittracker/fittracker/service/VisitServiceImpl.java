package com.fittracker.fittracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.UserDAO;
import com.fittracker.fittracker.dao.CompanyDAO;
import com.fittracker.fittracker.dao.VisitDAO;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Company;
import com.fittracker.fittracker.entity.Visit;
import com.fittracker.fittracker.repository.VisitRepository;

@Service
@Component("visitService")
public class VisitServiceImpl implements Services<Visit> {

	@Autowired
	private VisitDAO visitDao;

	private VisitRepository visitRepository;

	public VisitServiceImpl(VisitRepository theVisitRepository) {
		visitRepository = theVisitRepository;
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

	public void allActivityTo0(int userId) {
		visitDao.setActiveUserToZero(userId);
	}

	@Override
	public int getActiveVisit(int id) {
		int x = visitDao.getActiveVisitId(id);

		return x;
	}

	@Override
	public int getActiveVisitClub(int id, int clubId) {
		int x = visitDao.getActiveVisitIdClub(id, clubId);

		return x;
	}

}
