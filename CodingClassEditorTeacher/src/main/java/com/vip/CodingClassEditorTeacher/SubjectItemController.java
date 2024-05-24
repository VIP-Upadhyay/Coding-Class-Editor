package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.SubjectClass;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectItemController implements Initializable {
	
	@FXML
	Button delBtn,openBtnBtn;
	
	@FXML
	Label subject,semester,branch;
	
	@FXML
	Pane subPane;
	
	private TeacherService teacherService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		teacherService = RestApiUtils.getUserService();
		delBtn.setGraphic(App.loadGraphics("img/delico.png", 25));
	}
	
	void setData(SubjectClass subjectClass,VBox vBox,Pane homepane) {

		subject.setText(subjectClass.getClassName());
		semester.setText(subjectClass.getSemester());
		branch.setText(subjectClass.getBranch()); 
		
		delBtn.setOnAction(e->{
			ButtonType foo = new ButtonType("Delete", ButtonData.OK_DONE);
			ButtonType bar = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			Alert alert = new Alert(AlertType.CONFIRMATION,
			        "This action will remove all data present in this class.\n"
			        +"Are you really want to delete class?",
			        foo,
			        bar);

			alert.setTitle("Delete Class?");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setHeaderText(null);
			dialogPane.setGraphic(null);
			dialogPane.getStylesheets().add(
					   getClass().getResource("css/home.css").toExternalForm());
					dialogPane.getStyleClass().add("dialog-pane"); 
			Optional<ButtonType> result = alert.showAndWait();

			if (result.orElse(bar) == foo) {
				//code to delete from api 
				deleteSubject(subjectClass.getSubId());
				vBox.getChildren().remove(delBtn.getParent());
				System.out.println("delete subject id "+subjectClass.getSubId());
			}
		});
		
		openBtnBtn.setOnAction(e->{
			try {
				FXMLLoader loader = App.loadFXMLScreenLoader("subjectscreen");
				AnchorPane pane = loader.load();
				((Pane) homepane.getParent()).getChildren().add(pane); 
				AnchorPane.setTopAnchor(pane, 0.0);
				AnchorPane.setLeftAnchor(pane, 0.0);  
				AnchorPane.setRightAnchor(pane, 0.0);
				AnchorPane.setBottomAnchor(pane, 0.0); 
		    	((Pane) homepane.getParent()).getChildren().remove(homepane);
				SubjectController scontroller = (SubjectController)loader.getController();
				scontroller.setData(subjectClass);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	}

	private void deleteSubject(long subId) {
		// TODO Auto-generated method stub
		Call<Object> call = teacherService.delSubject(subId);
		call.enqueue(new Callback<Object>() {
			
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					System.out.println(response.body().toString());
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
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("exception....here....");
			}
		});
	}

}
