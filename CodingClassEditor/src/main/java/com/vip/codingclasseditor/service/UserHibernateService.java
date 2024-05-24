package com.vip.codingclasseditor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.repository.UserRepository;

@Service
@Transactional
public class UserHibernateService {
	private final UserRepository repository;

	public UserHibernateService(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	public UserProjection getUserForAuth(String userName,String email) {
		return repository.findUserForAuth(userName, email);
	}
	public boolean isUsernameExists(String userName) {
		return repository.existsByUserName(userName);
	}
	public boolean isEmailExists(String email) {
		return repository.existsByEmail(email);
	}
}
