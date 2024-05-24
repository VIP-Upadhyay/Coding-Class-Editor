package com.vip.codingclasseditor.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject_class")
public class SubjectClass {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_class_generator")
	private long subId;
	private String className;
	private Date classTime;
	private String semester;
	private String branch;
	@ManyToOne
	private Master master;
	@ManyToMany(mappedBy = "classes")
	private List<Student> students = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_Id")
	private List<Practical> practicals = new ArrayList<>();
	
	
	
	public List<Practical> getPracticals() {
		return practicals;
	}
	public void setPracticals(List<Practical> practicals) {
		this.practicals = practicals;
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
