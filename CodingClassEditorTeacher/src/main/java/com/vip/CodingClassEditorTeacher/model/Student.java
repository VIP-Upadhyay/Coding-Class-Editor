package com.vip.CodingClassEditorTeacher.model;

public class Student {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String rollNo;
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Student(Long userId, String firstName, String lastName, String email, String userName,String rollNo) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.rollNo = rollNo;
	}
	public Student(Long i,String firstName, String lastName, String userName,String rollNo) {
		super();
		this.userId = i;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.rollNo = rollNo;
	}


	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getRollNo() {
		return rollNo;
	}



	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	
	
}
