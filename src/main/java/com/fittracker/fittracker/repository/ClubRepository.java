package com.fittracker.fittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fittracker.fittracker.entity.Club;



public interface ClubRepository extends JpaRepository<Club, Integer> {

	
	//c'est tout, .... data jpa va faire les methodes crud necessaires
	
	
	

}
