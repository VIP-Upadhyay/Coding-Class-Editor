package com.vip.codingclasseditor.model;

import java.util.Date;

public class OTPmodel {

	int OTP;
	Date packageDate;
	Date expiryDate;
	
	public int getOTP() {
		return OTP;
	}
	public void setOTP(int oTP) {
		OTP = oTP;
	}
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "OTPmodel [OTP=" + OTP + ", packageDate=" + packageDate + ", expiryDate=" + expiryDate + "]";
	}

}
