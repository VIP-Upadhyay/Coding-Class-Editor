package com.vip.CodingClassEditorTeacher;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.utils.MasterHolder;

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
	Master student;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		slideShow();
		MasterHolder studentHolder = MasterHolder.getInstanceHolder();
		student = studentHolder.getMaster();
		name.setText(student.getFirstName()+" "+student.getLastName()); 
		username.setText(student.getUserName());   
		email.setText(student.getEmail());		 
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
