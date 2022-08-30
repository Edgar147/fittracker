package com.fittracker.fittracker.dao;

import com.fittracker.fittracker.entity.User;

public interface UserDAO {
	
	public void saveClient(User theClient);
	
    public User findByClientName(String userName);

    public void setCountOfClient(int i, String name);
}
