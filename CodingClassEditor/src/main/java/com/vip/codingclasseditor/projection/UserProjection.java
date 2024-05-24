package com.vip.codingclasseditor.projection;

import com.vip.codingclasseditor.model.Master;
import com.vip.codingclasseditor.model.Roles;
import com.vip.codingclasseditor.model.Student;

public class UserProjection {
	
	private long UserId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private Roles role;
	
	public UserProjection(long userId, String firstName, String lastName, String email, String userName,String password, Roles role) {
		super();
		UserId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public UserProjection(Master master) {
		UserId = master.getUserId();
		this.firstName = master.getFirstName();
		this.lastName = master.getLastName();
		this.email = master.getEmail();
		this.userName = master.getUserName();
		this.password = master.getPassword();
		this.role = master.getRole();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	public Master convertToMaster() {
		Master master = new Master();
		master.setFirstName(firstName);
		master.setLastName(lastName);
		master.setEmail(email);
		master.setPassword(password);
		master.setRole(role);
		master.setUserName(userName);
		master.setUserId(UserId);
		return master;
	}
	
	public Student convertToStudent() {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setUserName(userName);
		student.setPassword(password);
		student.setUserId(UserId);
		student.setRole(role);
		return student;
	}
}
