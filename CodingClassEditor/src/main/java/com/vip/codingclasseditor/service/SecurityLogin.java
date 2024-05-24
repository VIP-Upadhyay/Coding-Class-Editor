package com.vip.codingclasseditor.service;

public interface SecurityLogin {
	String findLoggedInUsername();
	void autoLogin(String username,String Password);
}
