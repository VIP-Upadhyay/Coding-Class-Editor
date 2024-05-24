package com.vip.CodingClassEditorTeacher.model;

import java.util.Date;


public class Practical {
	private long pracId;
	private String practicalName;
	private Date practicalDate;
	private String time;
	private SubjectClass subjectClass;
	
	public Practical() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Practical(long pracId, String practicalName, Date practicalDate, String time) {
		super();
		this.pracId = pracId;
		this.practicalName = practicalName;
		this.practicalDate = practicalDate;
		this.time = time;
	}

	public Practical(long pracId, String practicalName, Date practicalDate, String time, SubjectClass subjectClass) {
		super();
		this.pracId = pracId;
		this.practicalName = practicalName;
		this.practicalDate = practicalDate;
		this.time = time;
		this.subjectClass = subjectClass;
	}
	
	public long getPracId() {
		return pracId;
	}
	public void setPracId(long pracId) {
		this.pracId = pracId;
	}
	public String getPracticalName() {
		return practicalName;
	}
	public void setPracticalName(String practicalName) {
		this.practicalName = practicalName;
	}
	public Date getPracticalDate() {
		return practicalDate;
	}
	public void setPracticalDate(Date practicalDate) {
		this.practicalDate = practicalDate;
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
