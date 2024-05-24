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

import com.vip.codingclasseditor.projection.StudentListProjection;
import com.vip.codingclasseditor.projection.SubjectClassProjection;
import com.vip.codingclasseditor.service.SessionRenewal;
import com.vip.codingclasseditor.service.StudentHibernateService;

@RestController
public class StudentController {
	
	@Autowired
	StudentHibernateService studentHibernateService;
	
	@Autowired
	SessionRenewal renewal;
	
	@RequestMapping(value = "/getStudentForSubject/{page}",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public List<StudentListProjection> getStudentForSubjects(@RequestBody SubjectClassProjection projection,@PathVariable String page,Principal principal,HttpSession session){
		if (session.getAttribute("muser")!=null) {
			try {
				System.out.println("here");
				return studentHibernateService.getStudentForSubjects(projection.getBranch(), projection.getSemester(), Integer.parseInt(page));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {
			if (renewal.isMasterRenewal(principal.getName(), session)) {
				return getStudentForSubjects(projection, page, principal, session);
			}else {
				return null;
			}
		}
		
	}
}
