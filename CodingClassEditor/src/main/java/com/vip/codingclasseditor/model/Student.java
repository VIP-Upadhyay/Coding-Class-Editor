package com.vip.codingclasseditor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@DiscriminatorValue("Student")
public class Student extends User {
	
	private String branch;
	private String semester;
	private String rollNo;
	
	
	
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

	public void setRollNo(String roolNo) {
		this.rollNo = roolNo;
	}

	public void setClasses(List<SubjectClass> classes) {
		this.classes = classes;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Student_Classes",joinColumns = {@JoinColumn(name="user_id")},
	inverseJoinColumns = {@JoinColumn(name="sub_id")})
	private List<SubjectClass> classes = new ArrayList<>();

	public List<SubjectClass> getClasses() {
		return classes;
	}
	
	
}
