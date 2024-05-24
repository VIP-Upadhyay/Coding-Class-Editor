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

import com.vip.codingclasseditor.projection.AssignmentProjection;
import com.vip.codingclasseditor.service.AssignmentHibernateService;
import com.vip.codingclasseditor.service.SessionRenewal;

@RestController
public class AssignmentController {
	@Autowired
	private AssignmentHibernateService service;
	@Autowired
	SessionRenewal renewal;
	
	
	@RequestMapping(value = "/addAssignment",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public Object addPractical(@RequestBody AssignmentProjection practicalProjection,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			if (service.isAssignmentExists(practicalProjection.getAssignmentName(),practicalProjection.getSubjectClass())) {
				return "[\"assignment already exists\"]";
			}else {
				System.out.println(practicalProjection.getSubjectClass().getClassName());
				service.addAssigment(practicalProjection);
				return "[\"assignment added sucessfully\"]"; 
			}
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return addPractical(practicalProjection, principal, session);
			}else {
				return "[\"session is expired\"]";
			}
		}
		
	}
	
	@RequestMapping(value = "/getAssignment/{subId}/{page}",method = RequestMethod.POST,produces = "application/json")
	public List<AssignmentProjection> getAssignment(@PathVariable String subId,@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("muser")!=null) {
			try {
				return service.getAssignmentProjections(Long.parseLong(subId), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return getAssignment(subId,page, principal, session);
			}else {
				return null;
			}
		}
		
	}
	
	@RequestMapping(value = "/del/assignment/{pracId}",method = RequestMethod.GET,produces = "application/json")
	public Object delPracticalClass(@PathVariable String pracId,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			try {
				service.deleteAssignment(Integer.parseInt(pracId));
				return "[\"Assignment Deleted SuccessFully\"]";
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
