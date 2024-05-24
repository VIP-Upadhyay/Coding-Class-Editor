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

import com.vip.codingclasseditor.projection.PracticalProjection;
import com.vip.codingclasseditor.service.PracticalHibernateService;
import com.vip.codingclasseditor.service.SessionRenewal;

@RestController
public class PracticalController {
	
	@Autowired
	private PracticalHibernateService service;
	@Autowired
	SessionRenewal renewal;
	
	
	@RequestMapping(value = "/addPractical",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public Object addPractical(@RequestBody PracticalProjection practicalProjection,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			if (service.isPracticalExists(practicalProjection.getPracticalName(),practicalProjection.getSubjectClass())) {
				return "[\"practical already exists\"]";
			}else {
				System.out.println(practicalProjection.getSubjectClass().getClassName());
				service.addPractical(practicalProjection);
				return "[\"practical added sucessfully\"]";
			}
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return addPractical(practicalProjection, principal, session);
			}else {
				return "[\"session is expired\"]";
			}
		}
		
	}
	
	@RequestMapping(value = "/getPractical/{subId}/{page}",method = RequestMethod.POST,produces = "application/json")
	public List<PracticalProjection> getPracticals(@PathVariable String subId,@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("muser")!=null) {
			try {
				return service.getPracticals(Long.parseLong(subId), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return getPracticals(subId,page, principal, session);
			}else {
				return null;
			}
		}
		
	}
	
	@RequestMapping(value = "/del/practical/{pracId}",method = RequestMethod.GET,produces = "application/json")
	public Object delPracticalClass(@PathVariable String pracId,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			try {
				service.deletePractical(Integer.parseInt(pracId));
				return "[\"Practical Deleted SuccessFully\"]";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "[\"Something went worng please try again later\"]";
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return delPracticalClass(pracId, principal, session);
			}else {
				return "[\"session is expired\"]";
			}
		}
	}
}
