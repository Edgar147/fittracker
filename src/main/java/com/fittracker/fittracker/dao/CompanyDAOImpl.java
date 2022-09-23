package com.fittracker.fittracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Company;

@Repository //
public class CompanyDAOImpl implements CompanyDAO {

	// define field for entityManager, EM is for working with entities(search by id,
	// remove...
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public CompanyDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//
	// //need to inject sessionFactory
	// @Autowired
	// private SessionFactory sessFact;

	@Override
	public void saveCompany(Company theCompany) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theCompany); // if id is 0->save, else->update

	}

	@Override
	public List<Company> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Company> theCompany;
		// now retrieve/read from database using username
		Query<Company> theQuery = currentSession.createQuery("from Company", Company.class);

		List<Company> directors = theQuery.getResultList();

		return directors;

	}

}
