package com.vip.CodingClassEditorStudent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.services.utils.StudentHolder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ProfileController implements Initializable {

	@FXML
	Label name,username,email,branch,semester;
	
	@FXML
	ImageView imgView; 
	private int counts=0;
	Student student;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		slideShow();
		StudentHolder studentHolder = StudentHolder.getInstanceHolder();
		student = studentHolder.getStudent();
		name.setText(student.getFirstName()+" "+student.getLastName()); 
		username.setText(student.getUserName());  
		email.setText(student.getEmail());
		branch.setText(student.getBranch());
		semester.setText(student.getSemester());		
	}
	private void slideShow() {
		imgView.setImage(App.loadImage("img/codeimg5.png"));
		imgView.setFitHeight(500);
		imgView.setFitWidth(500);
		ArrayList<Image> images = new ArrayList<>();
		images.add(App.loadImage("img/codeimg5.png"));
		images.add(App.loadImage("img/codeimg7.png"));
		images.add(App.loadImage("img/codeimg6.png"));
		images.add(App.loadImage("img/codeimg4.png"));
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2),event ->{
			imgView.setImage(images.get(counts));
			counts++;
			if (counts==4) {
				counts=0;
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
}
