package com.vip.codingclasseditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vip.codingclasseditor.projection.UserProjection;


@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserHibernateService dbServiceUser;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProjection projection;
		projection = dbServiceUser.getUserForAuth(username, username);
		if (projection==null) {		
			throw new UsernameNotFoundException("not found");		
		}
		return new UserPrincipal(projection);
	}

}
