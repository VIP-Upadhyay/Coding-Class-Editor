package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Assignment;
import com.vip.CodingClassEditorTeacher.model.SubjectClass;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentScreenController implements Initializable {

	@FXML
	Pane practicalpane;
	
	@FXML
	Label sbjprac;
	
	@FXML
	Button addPracticals;
	
	@FXML
	ScrollPane pracscrlPane;
	
	@FXML
	VBox pracvbox;
	
	private SubjectClass subjectClass;
	int srNo=1;
	private boolean isViewUpdating=false;
	private int count=0;
	private TeacherService teacherService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		teacherService = RestApiUtils.getUserService();
	}
	public void setData(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
		sbjprac.setText(subjectClass.getClassName()+" Assignment");
		pracvbox.setSpacing(10);
		updateView();
		pracscrlPane.setOnScroll(e->{
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
	
	private void updateView() {
		// TODO Auto-generated method stub
		Call<List<Assignment>> call = teacherService.getAssignmentList(subjectClass.getSubId(), count);
		call.enqueue(new Callback<List<Assignment>>() {
			
			@Override
			public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<Assignment> practicals = response.body();
					if (!practicals.isEmpty()) {
						for (Assignment practical : practicals) {
							drawStudent(practical);
						}
						count++;
						isViewUpdating=false;
					}else {
						System.out.println("its empty");
						isViewUpdating=false;
					}
				}else {
					try {
						System.out.println("here "+response.errorBody().string().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Assignment>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("hahahhhahah");
				t.printStackTrace();
			}
		});
	}
	
	private void drawStudent(Assignment practical) {
		practical.setSubjectClass(subjectClass);
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {	
			@Override
			public void run() {
				try {		
					FXMLLoader fxmlLoader =  App.loadFXMLItems("practicalitem");
					Pane pane = fxmlLoader.load();
					PracticalListController itemController = fxmlLoader.getController();
					itemController.setAData(practical, srNo,pracvbox,practicalpane);
					pracvbox.getChildren().add(pane); 
					srNo++; 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			}
		});
	}
	@FXML
	public void onAddPractical() {
		try {
			FXMLLoader fxmlLoader = App.loadFXMLItems("addassignment");
			Parent parent = fxmlLoader.load();
			AddAssignmentController practicalController =  fxmlLoader.getController();
			practicalController.setData(subjectClass);
			Scene scene = new Scene(parent, 544, 298);
			scene.getStylesheets().add(App.class.getResource("css/"+"application" + ".css").toExternalForm());
			Stage stage = new Stage(); 
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		    if (practicalController.isSuccessfullyAdded()) {
		    	System.out.println("added");
		    	FXMLLoader loader = App.loadFXMLScreenLoader("assignmentscreen");
		    	AnchorPane pane = loader.load();
		    	((Pane) practicalpane.getParent()).getChildren().add(pane);
				AnchorPane.setTopAnchor(pane, 0.0); 
				AnchorPane.setLeftAnchor(pane, 0.0);
				AnchorPane.setRightAnchor(pane, 0.0);
				AnchorPane.setBottomAnchor(pane, 0.0); 
		    	((Pane) practicalpane.getParent()).getChildren().remove(practicalpane);
		    	AssignmentScreenController scontroller = loader.getController();
				scontroller.setData(subjectClass);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
