package com.vip.CodingClassEditorTeacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class FaqController implements Initializable {

	@FXML
	WebView q1v,q2v,q3v;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		q1v.getEngine().load("https://www.youtube.com/embed/sXW2VLrQ3Bs"); 
		q2v.getEngine().load("https://www.youtube.com/embed/Kn1HF3oD19c"); 
		q3v.getEngine().load("https://www.youtube.com/embed/cL4GcZ6GJV8"); 
	}

}
