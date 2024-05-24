package com.vip.codingclasseditor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assignment_generator")
	private long assignId;
	private String assignmentName;
	private Date assignmentDate;
	private String time;
	@ManyToOne
	private SubjectClass subjectClass;
	public long getAssignId() {
		return assignId;
	}
	public void setAssignId(long assignId) {
		this.assignId = assignId;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public SubjectClass getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
	}
	
}
