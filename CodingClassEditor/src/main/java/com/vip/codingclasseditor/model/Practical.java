package com.vip.codingclasseditor.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "practical")
public class Practical {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pactical_generator")
	private long pracId;
	private String practicalName;
	private Date practicalDate;
	private String time;
	@ManyToOne
	private SubjectClass subjectClass;
	
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
