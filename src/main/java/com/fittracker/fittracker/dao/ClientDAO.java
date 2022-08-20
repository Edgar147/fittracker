package com.fittracker.fittracker.dao;

import com.fittracker.fittracker.entity.Client;

public interface ClientDAO {
	
	public void saveClient(Client theClient);
	
    public Client findByClientName(String userName);

    public void setCountOfClient(int i, String name);
}
