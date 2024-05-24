package com.vip.codingclasseditor.projection;

import com.vip.codingclasseditor.model.Roles;
import com.vip.codingclasseditor.model.Student;

public class StudentProjection {
	private long UserId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private Roles role;
	private String branch;
	private String semester;
	private String rollNo;
	
	public StudentProjection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentProjection(long userId, String firstName, String lastName, String email, String userName,
			String password, Roles role, String branch, String semester, String rollNo) {
		super();
		UserId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.branch = branch;
		this.semester = semester;
		this.rollNo = rollNo;
	}

	public StudentProjection(Student student) {
		super();
		this.UserId = student.getUserId();
		this.firstName = student.getFirstName();
		this.lastName = student.getUserName();
		this.email = student.getEmail();
		this.password = student.getPassword();
		this.role = student.getRole();
		this.rollNo = student.getRollNo();
		this.branch = student.getBranch();
		this.semester = student.getSemester();
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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	public UserProjection convertToUserProjection() {
		return new UserProjection(UserId, firstName, lastName, email, userName, password, role);
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
		student.setBranch(branch);
		student.setSemester(semester);
		student.setRollNo(rollNo);
		return student;
	}
}
