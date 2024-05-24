package com.vip.codingclasseditor.projection;

public class StudentListProjection {
	
	private long UserId;
	private String firstName;
	private String lastName;
	private String userName;
	private String rollNo;
	
	
	public StudentListProjection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentListProjection(long userId, String firstName, String lastName, String userName, String rollNo) {
		super();
		UserId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.rollNo = rollNo;
	}
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
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
