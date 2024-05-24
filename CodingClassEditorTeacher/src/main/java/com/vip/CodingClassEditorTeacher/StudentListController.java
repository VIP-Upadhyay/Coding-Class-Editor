package com.vip.CodingClassEditorTeacher;

import com.vip.CodingClassEditorTeacher.model.Student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentListController {
	
	@FXML
	Label srNo,studentName,rollNo,username;
	
	public void setData(Student student,int srNum) {
		srNo.setText(Integer.toString(srNum));
		studentName.setText(student.getFirstName()+" "+student.getLastName());
		rollNo.setText(student.getRollNo());
		username.setText(student.getUserName());
	}
}
