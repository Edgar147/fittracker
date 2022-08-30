package com.fittracker.fittracker.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fittracker.fittracker.entity.User;

@Repository //
public class UserDAOImpl implements UserDAO {

	// define field for entityManager, EM is for working with entities(search by id,
	// remove...
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//
	// //need to inject sessionFactory
	// @Autowired
	// private SessionFactory sessFact;

	@Override
	public void saveClient(User theClient) {

		// Session currentSession= sessFact.openSession();
		// currentSession.saveOrUpdate(theClient);

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theClient); // if id is 0->save, else->update

	}

	@Override
	public User findByClientName(String clientName) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where firstName=:uName", User.class);
		theQuery.setParameter("uName", clientName);
		User theClient = null;
		try {
			theClient = theQuery.getSingleResult();
		} catch (Exception e) {
			theClient = null;
		}

		return theClient;
	}

	@Override
	public void setCountOfClient(int count, String clientName) {
		Session currentSession = entityManager.unwrap(Session.class);

		// TO WORK THIS, I DELETED Client,class
		TypedQuery<User> theQuery = currentSession
				.createQuery(" update User u set u.count =: uCount where firstName=:uName")
				.setParameter("uName", clientName).setParameter("uCount", count);
		theQuery.executeUpdate();
	}

}
