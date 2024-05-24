package com.vip.CodingClassEditorTeacher.utils;

import com.vip.CodingClassEditorTeacher.service.RetrofitClient;
import com.vip.CodingClassEditorTeacher.service.TeacherService;

public class RestApiUtils {
	 public static final String BASE_URL = "http://localhost:8080";
	 public static final String IP_ADDRESS = "localhost:8080";
	 public static TeacherService getUserService(){
		 return RetrofitClient.getClient(BASE_URL).create(TeacherService.class);
	 }
}
