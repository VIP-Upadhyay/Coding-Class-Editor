package com.vip.codingclasseditor.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.projection.UserProjection;

@Service
public class SessionRenewal {
	
	@Autowired
	MasterHibernateService masterHibernateService;
	
	@Autowired
	StudentHibernateService studentHibernateService;
	
	public  boolean isMasterRenewal(String username,HttpSession session) {
		if (!username.equals("")) {
			UserProjection projection = masterHibernateService.getMasterByUsername(username);
			session.setAttribute("muser", projection);
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean isStudentRenewal(String username,HttpSession session) {
		if (!username.equals("")) {
			StudentProjection projection = studentHibernateService.getStudentByUsername(username);
			session.setAttribute("suser", projection);
			return true;
		}else {
			return false;
		}
	}
}
