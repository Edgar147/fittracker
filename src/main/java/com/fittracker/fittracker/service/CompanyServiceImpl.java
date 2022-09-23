package com.fittracker.fittracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.CompanyDAO;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Company;

@Service
@Component("companyService")
public class CompanyServiceImpl implements Services<Company> {

	@Autowired
	private CompanyDAO direcDAO;

	@Override
	public Company findById(int theId) {

		return null;
	}

	@Override
	public List<Company> findAll() {
		return direcDAO.findAll();
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
	public void save(Company theCompany) {
		direcDAO.saveCompany(theCompany);

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
