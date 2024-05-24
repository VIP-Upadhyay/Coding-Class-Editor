package com.vip.CodingClassEditorStudent.services.utils;

import com.vip.CodingClassEditorStudent.services.RetrofitClient;
import com.vip.CodingClassEditorStudent.services.StudentService;

public class RestApiUtils {
	 public static final String BASE_URL = "http://localhost:8080";
	 public static final String IP_ADDRESS = "localhost:8080";
	 public static StudentService getUserService(){
		 return RetrofitClient.getClient(BASE_URL).create(StudentService.class);
	 }
}
