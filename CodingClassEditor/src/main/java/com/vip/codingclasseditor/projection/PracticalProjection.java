package com.vip.codingclasseditor.projection;



import java.util.Date;

import com.vip.codingclasseditor.model.Practical;
import com.vip.codingclasseditor.model.SubjectClass;

public class PracticalProjection {

	private long pracId;
	private String practicalName;
	private Date practicalDate;
	private String time;
	private SubjectClass subjectClass;
	
	public PracticalProjection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PracticalProjection(long pracId, String practicalName, Date practicalDate, String time) {
		super();
		this.pracId = pracId;
		this.practicalName = practicalName;
		this.practicalDate = practicalDate;
		this.time = time;
	}

	public PracticalProjection(long pracId, String practicalName, Date practicalDate, String time,SubjectClass subjectClass) {
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

	public Practical convertToPractical() {
		Practical practical = new Practical();
		practical.setPracticalName(this.practicalName);
		practical.setPracticalDate(this.practicalDate);
		practical.setTime(this.time);
		practical.setSubjectClass(this.subjectClass);
		return practical;
	}
	
}
