package com.vip.codingclasseditor.controller.rest;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vip.codingclasseditor.projection.MessageDetails;
import com.vip.codingclasseditor.service.MessageHibernateService;
import com.vip.codingclasseditor.service.SessionRenewal;
import com.vip.codingclasseditor.websocket.model.PrivateMessage;

@RestController
public class MessageController {

	@Autowired
	MessageHibernateService service;
	
	@Autowired
	SessionRenewal renewal;
	
	@RequestMapping(value = "/getMessageForMaster/{page}",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public List<PrivateMessage> getMessageForMaster(@RequestBody MessageDetails projection,@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("muser")!=null) {
			try {
				return service.getMessages(projection.getSenderId(), projection.getReceiverId(), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return getMessageForMaster(projection, page, principal, session);
			}else {
				return null;
			}
		}
		
	}
	
	@RequestMapping(value = "/getMessageForStudent/{page}",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public List<PrivateMessage> getMessageForStudent(@RequestBody MessageDetails projection,@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("suser")!=null) {
			try {
				return service.getMessages(projection.getSenderId(), projection.getReceiverId(), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isStudentRenewal(principal.getName(), session)) {
				return getMessageForStudent(projection, page, principal, session);
			}else {
				return null;
			}
		}
		
	}
}
