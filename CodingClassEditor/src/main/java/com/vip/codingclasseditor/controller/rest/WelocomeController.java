package com.vip.codingclasseditor.controller.rest;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vip.codingclasseditor.model.Roles;
import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.service.MasterHibernateService;
import com.vip.codingclasseditor.service.StudentHibernateService;


@RestController
public class WelocomeController {
	@Autowired
	MasterHibernateService masterHibernateService;
	
	@Autowired
	StudentHibernateService studentHibernateService;
	
	@RequestMapping(value = "/",method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Object> welcomeMaster(Principal principal,HttpSession session) throws MessagingException {
		UserProjection projection = masterHibernateService.getMasterByUsername(principal.getName());
		if (projection.getRole()==Roles.MASTER) {
			session.setAttribute("suser", projection);
			return ResponseEntity.ok(projection);
		}else {
			return ResponseEntity.status(123).body("only for master");
		}
	}
	
	@RequestMapping(value = "/studentLogin",method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Object> welcomeStudent(Principal principal,HttpSession session) throws MessagingException {
		StudentProjection projection = studentHibernateService.getStudentByUsername(principal.getName());
		if (projection.getRole()==Roles.STUDENT) {
			session.setAttribute("suser", projection);
			return ResponseEntity.ok(projection);
		}else {
			return ResponseEntity.status(123).body("only for student");
		}
		
	}
}
