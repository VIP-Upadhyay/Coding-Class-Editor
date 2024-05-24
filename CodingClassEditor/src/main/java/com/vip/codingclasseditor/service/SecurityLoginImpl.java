package com.vip.codingclasseditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public class SecurityLoginImpl implements SecurityLogin {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService UserDetailsService;
	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
		return null;
	}

	@Override
	public void autoLogin(String username, String Password) {
		try {
			UserPrincipal userPrincipal = (UserPrincipal)UserDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, Password, userPrincipal.getAuthorities());
	        authManager.authenticate(usernamePasswordAuthenticationToken);
	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
		
}
