package com.vip.CodingClassEditorTeacher.model;

import java.util.Date;

public class Assignment {
	private long assignId;
	private String assignmentName;
	private Date assignmentDate;
	private String time;
	private SubjectClass subjectClass;
	
	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Assignment(long assignId, String assignmentName, Date assignmentDate, String time) {
		super();
		this.assignId = assignId;
		this.assignmentName = assignmentName;
		this.assignmentDate = assignmentDate;
		this.time = time;
	}



	public Assignment(long assignId, String assignmentName, Date assignmentDate, String time,
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
}
