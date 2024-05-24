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

import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.projection.SubjectClassProjection;
import com.vip.codingclasseditor.projection.SubjectProjection;
import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.service.SessionRenewal;
import com.vip.codingclasseditor.service.SubjectClassHibernateServices;

@RestController
public class SubjectClassController {

	@Autowired
	SubjectClassHibernateServices services;
	
	@Autowired
	SessionRenewal renewal;
	
	@RequestMapping(value = "/addClass",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public Object addClasses(@RequestBody SubjectClassProjection projection,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			UserProjection userProjection = (UserProjection) session.getAttribute("muser");
			projection.setMaster(userProjection.convertToMaster());
			if (services.isClassExists(projection.getClassName(), projection.getSemester(), projection.getBranch())) {
				return "[\"class already exists\"]";
			}else {
				services.addClass(projection);
				return "[\"class added sucessfully\"]";
			}
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return addClasses(projection, principal, session);
			}else {
				return "[\"session is expired\"]";
			}
		}
		
	}
	
	@RequestMapping(value = "/getSubject/{page}",method = RequestMethod.GET,produces = "application/json")
	public List<SubjectProjection> getSubjects(@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("muser")!=null) {
			try {
				String username = principal.getName();
				return services.getSubjects(username, Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return getSubjects(page, principal, session);
			}else {
				return null;
			}
		}
		
	}
	
	@RequestMapping(value = "/getSubjectForStudent/{page}",method = RequestMethod.GET,produces = "application/json")
	public List<SubjectProjection> getSubjectsForStudent(@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("suser")!=null) {
			try {
				StudentProjection studentProjection = (StudentProjection) session.getAttribute("suser");
				return services.getSubjectsForStudent(studentProjection.getBranch(),studentProjection.getSemester(), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isStudentRenewal(principal.getName(), session)) {
				return getSubjectsForStudent(page, principal, session);
			}else {
				return null;
			}
		}
		
	}
	
	@RequestMapping(value = "/del/subject/{subId}",method = RequestMethod.GET,produces = "application/json")
	public Object delSubjectClass(@PathVariable String subId,Principal principal,HttpSession session) {
		if (session.getAttribute("muser")!=null) {
			try {
				services.deleteSubjectClass(Integer.parseInt(subId));
				return "[\"Subject Deleted SuccessFully\"]";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "[\"Something went worng please try again later\"]";
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return delSubjectClass(subId, principal, session);
			}else {
				return "[\"session is expired\"]";
			}
		}
	}
	
	@RequestMapping(value = "/getMaster/{subId}",method = RequestMethod.GET,produces = "application/json")
	public Object getMasterId(@PathVariable String subId,Principal principal,HttpSession session) {
		if (session.getAttribute("suser")!=null) {
			try {
				
				return services.getMasterProjection(Integer.parseInt(subId));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "[\"Something went worng please try again later\"]";
		}else {
			if (renewal.isStudentRenewal(principal.getName(), session)) {
				return getMasterId(subId, principal, session);
			}else {
				return null;
			}
		}
	}
}
