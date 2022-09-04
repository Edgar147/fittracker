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

import com.fittracker.fittracker.dao.UserDAO;
import com.fittracker.fittracker.dao.RoleDAO;
import com.fittracker.fittracker.entity.User;
import com.fittracker.fittracker.entity.Club;
import com.fittracker.fittracker.entity.Direction;
import com.fittracker.fittracker.entity.Role;
import com.fittracker.fittracker.repository.UserRepository;

@Service
@Component("cliSer")
public class UserServiceImpl implements Services<User> {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	private UserRepository userRepository;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RoleDAO roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// VERY IMPORTANt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public UserServiceImpl(UserRepository ur) {
		this.userRepository = ur;
	}

	@Override
	public User findById(int theId) {
		User theUser = userRepository.findById(theId).get();
		return theUser;
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
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
	public void save(User theUser) {
		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		// theUser.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_MANAGER")));
		theUser.setRoles(Arrays.asList(roleDAO.findRoleByName(theUser.getRole())));
		userDAO.saveUser(theUser);

	}

	@Override
	public void deleteById(int t) {
		userRepository.deleteById(t);
	}

	@Override
	@Transactional
	public void setCount2(int i, String name) {
		userDAO.setCountOfUser(i, name);
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
