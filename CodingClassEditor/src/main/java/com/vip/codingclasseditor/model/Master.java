package com.vip.codingclasseditor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
@DiscriminatorValue("Master")
public class Master extends User {
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_Id")
	List<SubjectClass> SubjectClasses = new ArrayList<>();

	public List<SubjectClass> getSubjectClasses() {
		return SubjectClasses;
	}

	public void setSubjectClasses(List<SubjectClass> subjectClasses) {
		SubjectClasses = subjectClasses;
	}
	
	
}
