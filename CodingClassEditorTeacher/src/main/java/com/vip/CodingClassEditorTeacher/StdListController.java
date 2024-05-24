package com.vip.CodingClassEditorTeacher;

import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Student;
import com.vip.CodingClassEditorTeacher.service.WebSocketExp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StdListController implements Initializable {

	@FXML
	Label stdName,username;
	Student student;
	@FXML
	AnchorPane stdPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	void setData(Student student,WebSocketExp webSocketExp) {
		this.student = student;
		stdName.setText(student.getFirstName()+" "+student.getLastName());
		username.setText(student.getUserName());
		stdPane.setOnMouseClicked(e->{
			webSocketExp.setCurStudent(student);
		});
	}
	@FXML
	public void onStdClick() {
		
	}

}
