package com.fittracker.fittracker.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	

}
