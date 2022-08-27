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
import com.fittracker.fittracker.dao.RoleDAO;
import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Role;
import com.fittracker.fittracker.repository.ClientRepository;
@Service
@Component("cliSer")
public class ClientServiceImpl implements Services<Client> {

	@Autowired
	private ClientDAO clientDAO;
	
	
	@Autowired
	private RoleDAO roleDAO;
	
	private ClientRepository clientRepository;
	
	
	
	@Autowired
	private ClientDAO clientDao;

	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public ClientServiceImpl(ClientRepository cr) {
		this.clientRepository = cr;
	}

	@Override
	public Client findById(int theId) {
		 Client theClient= clientRepository.findById(theId).get();
		 return theClient;
	}


	@Override
	public List<Client> findAll() {
  
		
		return clientRepository.findAll();
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
//		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_MANAGER")));
		theClient.setRoles(Arrays.asList(roleDAO.findRoleByName(theClient.getRole())));
		clientDAO.saveClient(theClient);
		
	}

	@Override
	public void deleteById(int t) {
		clientRepository.deleteById(t);		
	}

	@Override
	@Transactional
	public void setCount2(int i, String name) {
		clientDAO.setCountOfClient(i, name);		
	}

	@Override
	public void allActivityTo0(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getActiveVisit(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getActiveVisitClub(int id, int clubId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
