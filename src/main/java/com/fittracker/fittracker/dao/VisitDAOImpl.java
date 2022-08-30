package com.fittracker.fittracker.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Visit;

@Repository //
public class VisitDAOImpl implements VisitDAO {

	@Autowired
	private EntityManager entityManager;


	
	
	
	
	@Override
	@Transactional
	public void setActiveClientToZero(int clientId) {
Session currentSession = entityManager.unwrap(Session.class);
	
		
				
		//TO WORK THIS, I DELETED Client,class
		TypedQuery<Visit> theQuery = currentSession.createQuery(" update Visit v set v.isActiveNow = 0 where clientId=:vClientId").setParameter("vClientId", clientId);
	theQuery.executeUpdate();
		
	}






	@Override
	public int getActiveVisitId(int clientId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Visit> theQuery = currentSession.createQuery("from Visit v where v.isActiveNow = 1 and v.clientId =: vClientId  ", Visit.class);
		theQuery.setParameter("vClientId", clientId);
		Visit theVisit = null;

		try {
			theVisit=theQuery.getSingleResult();
			return 1;
		} catch (NoResultException e) {
		    return 0;
		}

		
	}






	@Override
	public int getActiveVisitIdClub(int clientId, int clubId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Visit> theQuery = currentSession.createQuery("from Visit v where v.isActiveNow = 1 and v.clientId =: vClientId and  v.clubId =: vclubId").setParameter("vClientId", clientId).setParameter("vclubId", clubId);
		
		Visit theVisit = null;

		try {
			theVisit=theQuery.getSingleResult();
			return 1;
		} catch (NoResultException e) {
		    return 0;
		}
	}
	

}
