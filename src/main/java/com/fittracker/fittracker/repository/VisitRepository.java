package com.fittracker.fittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fittracker.fittracker.entity.Visit;



public interface VisitRepository extends JpaRepository<Visit, Integer> {

	
	//c'est tout, .... data jpa va faire les methodes crud necessaires
	
	
	

}
