package com.vip.codingclasseditor.projection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vip.codingclasseditor.model.Master;
import com.vip.codingclasseditor.model.Student;
import com.vip.codingclasseditor.model.SubjectClass;

public class SubjectClassProjection {
	private String className;
	private Date classTime;
	private String semester;
	private String branch;
	private Master master;
	private List<Student> students = new ArrayList<>();
	
	public SubjectClassProjection(String className, Date classTime, String semester, String branch, Master master,
			List<Student> students) {
		super();
		this.className = className;
		this.classTime = classTime;
		this.semester = semester;
		this.branch = branch;
		this.master = master;
		this.students = students;
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
	
	public SubjectClass convertSubjectClass() {
		SubjectClass sClass = new SubjectClass();
		sClass.setBranch(branch);
		sClass.setClassName(className);
		sClass.setClassTime(classTime);
		sClass.setMaster(master);
		sClass.setSemester(semester);
		sClass.setStudents(students);
	
		return sClass;
	}
}
