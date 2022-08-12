package com.fittracker.fittracker.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fittracker.fittracker.entity.Client;
import com.fittracker.fittracker.service.ClientService;

//import com.luv2code.springsecurity.demo.entity.User;
//import com.luv2code.springsecurity.demo.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		Client theUser = userService.findByClientName(userName);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("client", theUser);
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
