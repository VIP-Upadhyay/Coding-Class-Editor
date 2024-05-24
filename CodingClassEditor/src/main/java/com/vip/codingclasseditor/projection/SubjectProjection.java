package com.vip.codingclasseditor.projection;

public class SubjectProjection {
	private long subId;
	private String className;
	private String semester;
	private String branch;
	
	
	
	public SubjectProjection(long subId, String className, String semester, String branch) {
		super();
		this.subId = subId;
		this.className = className;
		this.semester = semester;
		this.branch = branch;
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
	
	
}
