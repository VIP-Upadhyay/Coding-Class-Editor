package com.vip.codingclasseditor.projection;

import java.util.Date;

import com.vip.codingclasseditor.model.Assignment;
import com.vip.codingclasseditor.model.SubjectClass;

public class AssignmentProjection {
	private long assignId;
	private String assignmentName;
	private Date assignmentDate;
	private String time;
	private SubjectClass subjectClass;
	
	public AssignmentProjection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AssignmentProjection(long assignId, String assignmentName, Date assignmentDate, String time) {
		super();
		this.assignId = assignId;
		this.assignmentName = assignmentName;
		this.assignmentDate = assignmentDate;
		this.time = time;
	}



	public AssignmentProjection(long assignId, String assignmentName, Date assignmentDate, String time,
			SubjectClass subjectClass) {
		super();
		this.assignId = assignId;
		this.assignmentName = assignmentName;
		this.assignmentDate = assignmentDate;
		this.time = time;
		this.subjectClass = subjectClass;
	}

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
	
	public Assignment convertToAssignment() {
		Assignment practical = new Assignment();
		practical.setAssignmentName(this.assignmentName);
		practical.setAssignmentDate(this.assignmentDate);
		practical.setTime(this.time);
		practical.setSubjectClass(this.subjectClass);
		return practical;
	}
}
