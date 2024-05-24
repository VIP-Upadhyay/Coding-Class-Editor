package com.vip.codingclasseditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vip.codingclasseditor.model.Master;
import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.repository.MasterRepository;

@Service
@Transactional
public class MasterHibernateService {
	
	private final MasterRepository masterRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public MasterHibernateService(MasterRepository masterRepository) {
		super();
		this.masterRepository = masterRepository;
	}
	public UserProjection saveMaster(UserProjection userProjection) {
		Master master = userProjection.convertToMaster();
		master.setPassword(bcrypt.encode(userProjection.getPassword()));
		return new UserProjection(masterRepository.save(master));
	}
	
	public UserProjection getMasterByUsername(String username) {
		return masterRepository.findByUsername(username);
	}
	

}
