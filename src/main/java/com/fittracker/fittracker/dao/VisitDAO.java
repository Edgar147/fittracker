package com.fittracker.fittracker.dao;


public interface VisitDAO {
	

    public void setActiveClientToZero(int clientId);
    
    public int getActiveVisitId(int clientId);
    public int getActiveVisitIdClub(int clientId,int clubId);
}
