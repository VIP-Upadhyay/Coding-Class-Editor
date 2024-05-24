package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.SubjectClass;
import com.vip.CodingClassEditorStudent.services.StudentService;
import com.vip.CodingClassEditorStudent.services.utils.RestApiUtils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenController implements Initializable {

	

	@FXML
	Label sbj;
	
	@FXML
	ScrollPane scrlPane;
	
	@FXML 
	AnchorPane homepane;
	
	@FXML
	VBox vsubbox;

	
	@FXML
	Pane imgSliderPane;
	
	@FXML
	ImageView imgView;
	
	private boolean isViewUpdating=false;
	private int count=0,counts=0; 
	private StudentService studentService;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		slideShow();
		studentService = RestApiUtils.getUserService();
		
		
		vsubbox.setSpacing(10);
		updateView();
		scrlPane.setOnScroll(e->{
			if(e.getDeltaY()<0) {
				System.out.println("bottom");
				if(!isViewUpdating) {
					isViewUpdating=true; 
					updateView();  
				}else {
					System.out.println("still updating");
				}
			}
		});
		
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
	
	
	private void updateView() {
		// TODO Auto-generated method stub
		Call<List<SubjectClass>> call = studentService.getSubjects(count);
		call.enqueue(new Callback<List<SubjectClass>>() {
			
			@Override
			public void onResponse(Call<List<SubjectClass>> call, Response<List<SubjectClass>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<SubjectClass> subjectClasses = response.body();
					if (!subjectClasses.isEmpty()) {
						for (SubjectClass subjectClass : subjectClasses) {
							drawClass(subjectClass);
						}
						count++;
						isViewUpdating=false;
					}else {
						System.out.println("its empty");
						isViewUpdating=false;
					}
				}else {
					try {
						System.out.println(response.errorBody().string().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void onFailure(Call<List<SubjectClass>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("exception....");
			}
		});
		
	}
	
	void drawClass(SubjectClass subjectClass){
		Platform.runLater(new Runnable() {	
			@Override
			public void run() {
				try {		
					FXMLLoader fxmlLoader =  App.loadFXMLItems("subjectitem");
					Pane pane = fxmlLoader.load();
					SubjectItemController itemController = fxmlLoader.getController();
					itemController.setData(subjectClass,vsubbox,homepane);
					vsubbox.getChildren().add(pane);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			}
		});
	}
	
}
