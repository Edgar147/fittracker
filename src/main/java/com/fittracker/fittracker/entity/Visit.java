package com.fittracker.fittracker.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="visit")
public class Visit {
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name="client_id")
	private int clientId;
	
	@Column(name="club_id")
	private int clubId;
	
	@Column(name="start_date")
	private LocalDateTime startDate;
	
	@Column(name="end_date")
	private LocalDateTime endDate;
	
	@Column(name = "duration")
	private Float duration;

	
	
	public Visit(int clientId, int clubId, LocalDateTime startDate ) {
		this.clientId = clientId;
		this.clubId = clubId;
		this.startDate = startDate;

	}
	
	
	
	public Visit(Long id, int clientId, int clubId, LocalDateTime startDate, LocalDateTime endDate, Float duration) {
//		this.id = id;
		this.clientId = clientId;
		this.clubId = clubId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}
	
	

	
	
	
	
	
	

	
	
	

}