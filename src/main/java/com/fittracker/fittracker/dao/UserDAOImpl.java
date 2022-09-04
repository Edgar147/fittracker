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
	public void saveUser(User theUser) {

		// Session currentSession= sessFact.openSession();
		// currentSession.saveOrUpdate(theUser);

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theUser); // if id is 0->save, else->update

	}

	@Override
	public User findByUserName(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where firstName=:uName", User.class);
		theQuery.setParameter("uName", userName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void setCountOfUser(int count, String userName) {
		Session currentSession = entityManager.unwrap(Session.class);

		// TO WORK THIS, I DELETED User.class
		TypedQuery<User> theQuery = currentSession
				.createQuery(" update User u set u.count =: uCount where firstName=:uName")
				.setParameter("uName", userName).setParameter("uCount", count);
		theQuery.executeUpdate();
	}

}
