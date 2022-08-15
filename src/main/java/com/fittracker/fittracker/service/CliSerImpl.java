package com.fittracker.fittracker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.dao.RoleDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Role;
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
//HAVE THIS UTILITY??????????????
@Service
public class CliSerImpl implements ClientService{

	// need to inject user dao
		@Autowired
		private ClientDAO clientDao;

		@Autowired
		private RoleDAO roleDao;
		
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		@Override
		@Transactional
		public Client findByClientName(String userName) {
			// check the database if the user already exists
			return clientDao.findByClientName(userName);
		}

		@Override
		@Transactional
		public void save(Client theClient) {
			theClient.setPassword(passwordEncoder.encode(theClient.getPassword()));
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxxxxxxxxxxxxxx");
			clientDao.saveClient(theClient);
		}

		@Override
		@Transactional
		public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			Client user = clientDao.findByClientName(userName);
			if (user == null) {
				throw new UsernameNotFoundException("Invalid username or password.");
			}
			return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
		}

		private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		}

}