package com.fittracker.fittracker.dao;

import java.util.List;

import com.fittracker.fittracker.entity.Company;

public interface CompanyDAO {

	public void saveCompany(Company theCompany);

	public List<Company> findAll();

}
