package com.vip.codingclasseditor.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.vip.codingclasseditor.model.OTPmodel;
import com.vip.codingclasseditor.projection.UserProjection;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendOtpUsingMail(UserProjection user,OTPmodel otp) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setTo(user.getEmail());
	    helper.setSubject("Here's your One Time Password (OTP) - Expire in 5 minutes!");
	    String content = "<p>Hello " + user.getFirstName()+" "+user.getLastName() + "</p>"
	            + "<p>Your one time password is <b>"+otp.getOTP()+"</b></p>"
	            + "<br>"
	            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
	    helper.setText(content, true);
	    javaMailSender.send(message);
	}
}
