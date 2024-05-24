package com.vip.CodingClassEditorTeacher.service;

import java.util.ArrayList;
import java.util.List;

import com.vip.CodingClassEditorTeacher.model.Assignment;
import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.model.MessageDetails;
import com.vip.CodingClassEditorTeacher.model.OTPmodel;
import com.vip.CodingClassEditorTeacher.model.Practical;
import com.vip.CodingClassEditorTeacher.model.PrivateMessage;
import com.vip.CodingClassEditorTeacher.model.Student;
import com.vip.CodingClassEditorTeacher.model.SubjectClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TeacherService {
	
	@GET("/")
	Call<Master> welcome(@Header("Authorization") String credential);
	
	@POST("/registerMaster")
	Call<ArrayList<String>> registerMaster(@Body Master master);
	
	@POST("/verify-otp")
	Call<Object> verifyOTP(@Body OTPmodel model);
	
	@POST("/addClass")
	Call<Object> addClass(@Body SubjectClass classs);
	
	@GET("/getSubject/{page}")
	Call<List<SubjectClass>> getSubjects(@Path("page") int page);
	
	@POST("/getStudentForSubject/{page}")
	Call<List<Student>> getStudentList(@Body SubjectClass classs,@Path("page") int page);
	
	@GET("/del/subject/{subId}")
	Call<Object> delSubject(@Path("subId") Long id);
	
	@POST("/addPractical")
	Call<Object> addPractical(@Body Practical practical);
	
	@POST("/getPractical/{subId}/{page}")
	Call<List<Practical>> getPracticalList(@Path("subId") long subId,@Path("page") int page);
	
	@GET("/del/practical/{pracId}")
	Call<Object> delPractical(@Path("pracId") Long id);
	
	@POST("/addAssignment")
	Call<Object> addAssignment(@Body Assignment practical);
	
	@POST("/getAssignment/{subId}/{page}")
	Call<List<Assignment>> getAssignmentList(@Path("subId") long subId,@Path("page") int page);
	
	@GET("/del/assignment/{pracId}")
	Call<Object> delAssignment(@Path("pracId") Long id);
	
	@POST("/getMessageForMaster/{page}")
	Call<List<PrivateMessage>> getMessages(@Body MessageDetails details,@Path("page") int page);
}
