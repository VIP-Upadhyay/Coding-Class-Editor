package com.vip.CodingClassEditorStudent.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class SubjectClass {

	private long subId;
	private String className;
	private Date classTime;
	private String semester;
	private String branch;
	private Master master;
	private List<Student> students = new ArrayList<>();
	
	
	
	public SubjectClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SubjectClass(long subId, String className, Date classTime, String semester, String branch, Master master,
			List<Student> students) {
		super();
		this.subId = subId;
		this.className = className;
		this.classTime = classTime;
		this.semester = semester;
		this.branch = branch;
		this.master = master;
		this.students = students;
	}


	public long getSubId() {
		return subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getClassTime() {
		return classTime;
	}
	public void setClassTime(Date classTime) {
		this.classTime = classTime;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
