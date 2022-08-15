package com.fittracker.fittracker.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittracker.fittracker.dao.ClientDAO;
import com.fittracker.fittracker.dao.ClientRepository;
import com.fittracker.fittracker.dao.RoleDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Role;
@Service
@Component("cliSer")
public class ClientServiceImpl implements Services<Client> {

	@Autowired
	private ClientDAO clientDAO;
	
	
	@Autowired
	private RoleDAO roleDAO;
	
	private ClientRepository cl;
	
	
	
	@Autowired
	private ClientDAO clientDao;

	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public ClientServiceImpl(ClientRepository cl) {
		this.cl = cl;
	}

	@Override
	public Club findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String findNameById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Client> findAll() {
  
		
		return cl.findAll();
	}


	@Override
	public Client findClientById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public Client findByClientName(String userName) {
		return clientDao.findByClientName(userName);
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


	@Override
	@Transactional
	public void save(Client theClient) {
		theClient.setPassword(passwordEncoder.encode(theClient.getPassword()));
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		System.out.println(theClient.getRole());
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");

//		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_MANAGER")));
		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName(theClient.getRole())));
		clientDAO.saveClient(theClient);		
	}

}
