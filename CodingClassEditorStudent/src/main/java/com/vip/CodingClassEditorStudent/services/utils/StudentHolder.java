package com.vip.CodingClassEditorStudent.services.utils;

import com.vip.CodingClassEditorStudent.model.Student;

public final class StudentHolder {
	private Student student;
	private final static StudentHolder INSTANCE_HOLDER = new StudentHolder();
	
	public static StudentHolder getInstanceHolder() {
		return INSTANCE_HOLDER;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}	
	
}
