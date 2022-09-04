package com.fittracker.fittracker.dao;

import com.fittracker.fittracker.entity.User;

public interface UserDAO {

    public void saveUser(User theUser);

    public User findByUserName(String userName);

    public void setCountOfUser(int i, String name);
}
