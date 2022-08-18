package com.fittracker.fittracker.dao;

import java.util.List;

import com.fittracker.fittracker.entity.Direction;

public interface DirectionDAO {
	
	public void saveDirection(Direction theDirection);
	
	public List<Direction> findAll();
	
	
}
