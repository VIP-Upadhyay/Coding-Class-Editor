package com.vip.codingclasseditor.controller.rest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vip.codingclasseditor.component.OTPgenerator;
import com.vip.codingclasseditor.model.OTPmodel;
import com.vip.codingclasseditor.model.Roles;
import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.service.EmailService;
import com.vip.codingclasseditor.service.StudentHibernateService;
import com.vip.codingclasseditor.service.UserHibernateService;

@RestController
public class RegisterController {

	@Autowired
	UserHibernateService dbServiceUser;
	@Autowired 
	StudentHibernateService sService;
	@Autowired
	OTPgenerator otpGenerator;
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/registerMaster",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public String registerMaster(@RequestBody UserProjection user,HttpSession session) throws MessagingException{
		if (dbServiceUser.isUsernameExists(user.getUserName())) {
			return "[\"username is exists\"]";
		}else {
			if (dbServiceUser.isEmailExists(user.getEmail())) {
				return "[\"email is exists\"]";
			}else {
				OTPmodel otPmodel = otpGenerator.generate();
				user.setRole(Roles.MASTER);
				session.setAttribute("otp", otPmodel);
				session.setAttribute("muser", user);
				emailService.sendOtpUsingMail(user, otPmodel);
				System.out.println(otPmodel);
				return "[\"otp successfully sendend\"]";
			}
		}
	}
	
	@RequestMapping(value = "/registerStudent",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public String registerStudent(@RequestBody StudentProjection user,HttpSession session) throws MessagingException{
		if (dbServiceUser.isUsernameExists(user.getUserName())) {
			return "[\"username is exists\"]";
		}else {
			if (dbServiceUser.isEmailExists(user.getEmail())) {
				return "[\"email is exists\"]";
			}else {
				user.setRole(Roles.STUDENT);
				session.setAttribute("suser", user);
				return "[\"user added successfully\"]";
			}
		}
	}
	
	@RequestMapping(value = "/registerStudentData",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public String registerStudentData(@RequestBody StudentProjection detail,HttpSession session) throws MessagingException{
		
		if (session!=null) {
			if (session.getAttribute("suser")!=null) {
				if (sService.isDetailsExists(detail.getRollNo(), detail.getBranch(), detail.getSemester())) {
					return "[\"this Roll No is already signin\"]";
				}else {
					StudentProjection user = (StudentProjection) session.getAttribute("suser");
					user.setRollNo(detail.getRollNo());
					user.setBranch(detail.getBranch());
					user.setSemester(detail.getSemester());
					OTPmodel otPmodel = otpGenerator.generate();
					session.setAttribute("otp", otPmodel);
					session.removeAttribute("suser");
					session.setAttribute("suser", user);
					System.out.println(user.getPassword());
					emailService.sendOtpUsingMail(user.convertToUserProjection(), otPmodel);
					System.out.println(otPmodel);
					return "[\"otp successfully sendend\"]";
				}
				
			}else {
				return "[\"user or otp is not set\"]";
			}
		}else {
			return "[\"session id null\"]";
		}
	}
}
