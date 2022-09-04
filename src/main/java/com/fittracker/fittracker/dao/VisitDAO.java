package com.fittracker.fittracker.dao;

public interface VisitDAO {

    public void setActiveUserToZero(int userId);

    public int getActiveVisitId(int userId);

    public int getActiveVisitIdClub(int userId, int clubId);
}
