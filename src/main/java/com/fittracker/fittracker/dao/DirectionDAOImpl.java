package com.fittracker.fittracker.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Direction;

@Repository //
public class DirectionDAOImpl implements DirectionDAO {

	
	//define field for entityManager,  EM is for working with entities(search by id, remove...
		private EntityManager entityManager;
		
		
		//set up constructor injection
		@Autowired
		public DirectionDAOImpl(EntityManager theEntityManager) {
			entityManager = theEntityManager;
		}
		
	
	
	
//	
//	//need to inject sessionFactory
//	@Autowired
//	private SessionFactory sessFact;
	
	
	@Override
	public void saveDirection(Direction theDirection) {
		
//		Session currentSession= sessFact.openSession();
		//		currentSession.saveOrUpdate(theClient);

		Session  currentSession= entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theDirection); //if id is 0->save, else->update

		
	}






}
