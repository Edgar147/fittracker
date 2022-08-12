package com.fittracker.fittracker.dao;

import com.fittracker.fittracker.entity.Role;


public interface RoleDAO {

	public Role findRoleByName(String theRoleName);
	
}
