package com.vip.CodingClassEditorStudent.services;

import java.util.ArrayList;
import java.util.List;

import com.vip.CodingClassEditorStudent.model.Assignment;
import com.vip.CodingClassEditorStudent.model.MessageDetails;
import com.vip.CodingClassEditorStudent.model.OTPmodel;
import com.vip.CodingClassEditorStudent.model.Practical;
import com.vip.CodingClassEditorStudent.model.PrivateMessage;
import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.model.SubjectClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentService {

	@GET("/studentLogin")
	Call<Student> welcome(@Header("Authorization") String credential);
	
	@POST("/registerStudent")
	Call<ArrayList<String>> registerStudent(@Body Student master);
	
	@POST("/registerStudentData")
	Call<Object> addDetails(@Body Student student);
	
	@POST("/verify-otp-std")
	Call<Object> verifyOTP(@Body OTPmodel model);
	
	@GET("/getSubjectForStudent/{page}")
	Call<List<SubjectClass>> getSubjects(@Path("page") int page);
	
	@POST("/getPractical/{subId}/{page}")
	Call<List<Practical>> getPracticalList(@Path("subId") long subId,@Path("page") int page);
	
	@POST("/getAssignment/{subId}/{page}")
	Call<List<Assignment>> getAssignment(@Path("subId") long subId,@Path("page") int page);
	
	@GET("/getMaster/{subId}")
	Call<Object> getMaster(@Path("subId") Long id);
	
	@POST("/getMessageForStudent/{page}")
	Call<List<PrivateMessage>> getMessages(@Body MessageDetails details,@Path("page") int page);
}
