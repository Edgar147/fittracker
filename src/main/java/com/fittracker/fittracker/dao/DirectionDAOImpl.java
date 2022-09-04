package com.fittracker.fittracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Direction;

@Repository //
public class DirectionDAOImpl implements DirectionDAO {

	// define field for entityManager, EM is for working with entities(search by id,
	// remove...
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public DirectionDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//
	// //need to inject sessionFactory
	// @Autowired
	// private SessionFactory sessFact;

	@Override
	public void saveDirection(Direction theDirection) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theDirection); // if id is 0->save, else->update

	}

	@Override
	public List<Direction> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Direction> theDirection;
		// now retrieve/read from database using username
		Query<Direction> theQuery = currentSession.createQuery("from Direction", Direction.class);

		List<Direction> directors = theQuery.getResultList();

		return directors;

	}

}
