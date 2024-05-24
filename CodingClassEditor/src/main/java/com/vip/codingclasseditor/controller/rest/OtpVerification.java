package com.vip.codingclasseditor.controller.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vip.codingclasseditor.model.OTPmodel;
import com.vip.codingclasseditor.projection.StudentProjection;
import com.vip.codingclasseditor.projection.UserProjection;
import com.vip.codingclasseditor.service.MasterHibernateService;
import com.vip.codingclasseditor.service.SecurityLogin;
import com.vip.codingclasseditor.service.StudentHibernateService;


@RestController
public class OtpVerification {
	@Autowired
	SecurityLogin security;
	@Autowired
	MasterHibernateService mDBService;
	@Autowired
	StudentHibernateService sDBService;

	@RequestMapping(value = "/verify-otp",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public Object verifyOTP(@RequestBody OTPmodel otp,HttpSession session) throws JsonProcessingException {
		if (session!=null) {
			if (session.getAttribute("muser")!=null&&session.getAttribute("otp")!=null) {
				OTPmodel otPmodel = (OTPmodel) session.getAttribute("otp");
				System.out.println(otPmodel.getOTP()+" = "+otp);
				if (otPmodel.getOTP() == otp.getOTP()) {
					System.out.println("success");
					UserProjection projection =(UserProjection) session.getAttribute("muser");
					Object returnProjection= mDBService.saveMaster(projection);
					security.autoLogin(projection.getUserName(), projection.getPassword());
					session.removeAttribute("otp");
					session.setAttribute("muser", returnProjection);
					System.out.println(returnProjection);
					System.out.print("otp verified");
					return returnProjection;
				}else {
					System.out.println("failure");
					return "[\"otp not matched\"]";
				}
			}else {
				return "[\"user or otp is not set\"]";
			}
		}else {
			return "[\"session id null\"]";
		}
	}
	
	@RequestMapping(value = "/verify-otp-std",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public Object verifyOTPStd(@RequestBody OTPmodel otp,HttpSession session) throws JsonProcessingException {
		if (session!=null) {
			if (session.getAttribute("suser")!=null&&session.getAttribute("otp")!=null) {
				String returnString = "";
				OTPmodel otPmodel = (OTPmodel) session.getAttribute("otp");
				System.out.println(otPmodel.getOTP()+" = "+otp);
				if (otPmodel.getOTP() == otp.getOTP()) {
					System.out.println("success");
					StudentProjection projection =(StudentProjection) session.getAttribute("suser");
					Object returnProjection = sDBService.saveStudent(projection);
					security.autoLogin(projection.getUserName(), projection.getPassword());
					session.removeAttribute("otp");
					System.out.println(returnProjection);
					System.out.print("otp verified");
					return returnProjection;
				}else {
					System.out.println("failure");
					returnString = "[\"otp not matched\"]";
				}
				System.out.println("otp verified");
				return returnString;
			}else {
				return "[\"user or otp is not set\"]";
			}
		}else {
			return "[\"session id null\"]";
		}
	}
}
