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
	public void setActiveUserToZero(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// TO WORK THIS, I DELETED User.class
		TypedQuery<Visit> theQuery = currentSession
				.createQuery(" update Visit v set v.isActiveNow = 0 where userId=:vUserId")
				.setParameter("vUserId", userId);
		theQuery.executeUpdate();

	}

	@Override
	public int getActiveVisitId(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Visit> theQuery = currentSession
				.createQuery("from Visit v where v.isActiveNow = 1 and v.userId =: vUserId  ", Visit.class);
		theQuery.setParameter("vUserId", userId);
		Visit theVisit = null;

		try {
			theVisit = theQuery.getSingleResult();
			return 1;
		} catch (NoResultException e) {
			return 0;
		}

	}

	@Override
	public int getActiveVisitIdClub(int userId, int clubId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Visit> theQuery = currentSession
				.createQuery("from Visit v where v.isActiveNow = 1 and v.userId =: vUserId and  v.clubId =: vclubId")
				.setParameter("vUserId", userId).setParameter("vclubId", clubId);

		Visit theVisit = null;

		try {
			theVisit = theQuery.getSingleResult();
			return 1;
		} catch (NoResultException e) {
			return 0;
		}
	}

}
