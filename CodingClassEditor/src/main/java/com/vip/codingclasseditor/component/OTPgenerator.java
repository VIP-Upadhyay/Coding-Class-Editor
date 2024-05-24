package com.vip.codingclasseditor.component;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.vip.codingclasseditor.model.OTPmodel;

@Component
public class OTPgenerator {

	public OTPmodel generate() {
		Date packed = new Date();
		OTPmodel model = new OTPmodel();
		model.setPackageDate(packed);
		int otp = Integer.parseInt(String.valueOf(otp(6)));
		int len = String.valueOf(otp).length();
		if(len<6) {
			return generate();
		}else {
			model.setOTP(otp);
			long currentTime = packed.getTime();
			Date expiryDate = new Date(currentTime+(3*60000));
			model.setExpiryDate(expiryDate);
			return model;
		}
	}
	public  char[] otp(int len) 
    { 
        String numbers = "0123456789"; 
        Random rndm_method = new Random(); 
        char[] otp = new char[len]; 
        for (int i = 0; i < len; i++) 
        { 
            otp[i] = 
             numbers.charAt(rndm_method.nextInt(numbers.length())); 
        } 
        return otp; 
    } 

}
