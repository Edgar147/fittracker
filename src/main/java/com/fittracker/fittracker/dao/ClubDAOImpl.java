package com.fittracker.fittracker.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;

@Repository //
public class ClubDAOImpl implements ClubDAO {

	// define field for entityManager, EM is for working with entities(search by id,
	// remove...
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public ClubDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void saveClub(Club theClub) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theClub); // if id is 0->save, else->update
	}

}
