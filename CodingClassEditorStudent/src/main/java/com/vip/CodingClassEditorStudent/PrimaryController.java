package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.services.utils.StudentHolder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PrimaryController implements Initializable {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentHolder studentHolder = StudentHolder.getInstanceHolder();
		Student student = (Student) studentHolder.getStudent();
		System.out.println(student.getEmail());
		
	}
}
