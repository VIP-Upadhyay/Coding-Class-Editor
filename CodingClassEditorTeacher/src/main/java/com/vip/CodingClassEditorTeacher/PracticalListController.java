package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Assignment;
import com.vip.CodingClassEditorTeacher.model.Practical;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class PracticalListController implements Initializable{
	@FXML
	Label srNo,practicalName,practicalDate,time;
	
	@FXML
	Button popenbtn,delBtn;
	
	private TeacherService teacherService;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		teacherService = RestApiUtils.getUserService();
		delBtn.setGraphic(App.loadGraphics("img/delico.png", 25));
	}

	void setData(Practical practical, int srNo2, VBox vBox,Pane homepane) {
		// TODO Auto-generated method stub
		srNo.setText(Integer.toString(srNo2));
		practicalName.setText(practical.getPracticalName());
		practicalDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(practical.getPracticalDate()));
		time.setText(practical.getTime());
		
		delBtn.setOnAction(e->{
			ButtonType foo = new ButtonType("Delete", ButtonData.OK_DONE);
			ButtonType bar = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			Alert alert = new Alert(AlertType.CONFIRMATION,
			        "This action will remove all data present in this practical.\n"
			        +"Are you really want to delete practical?",
			        foo,
			        bar);

			alert.setTitle("Delete Practical?");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.setHeaderText(null);
			dialogPane.setGraphic(null);
			dialogPane.getStylesheets().add(
					   getClass().getResource("css/home.css").toExternalForm());
					dialogPane.getStyleClass().add("dialog-pane"); 
			Optional<ButtonType> result = alert.showAndWait();

			if (result.orElse(bar) == foo) {
				//code to delete from api 
				deletePractical(practical.getPracId());
				vBox.getChildren().remove(delBtn.getParent());
				System.out.println("delete subject id "+practical.getPracId());
			}
		});
		popenbtn.setOnAction(e->{
//			try {
//				FXMLLoader loader = App.loadFXMLScreenLoader("editorscreen");
//				AnchorPane pane = loader.load();
//				((Pane) homepane.getParent()).getChildren().add(pane);
//				AnchorPane.setTopAnchor(pane, 0.0); 
//				AnchorPane.setLeftAnchor(pane, 0.0);
//				AnchorPane.setRightAnchor(pane, 0.0);
//				AnchorPane.setBottomAnchor(pane, 0.0);
//		    	((Pane) homepane.getParent()).getChildren().remove(homepane);
//				EditorController controller = loader.getController();
//				controller.setData(practical);
//				
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		});
	}

	private void deletePractical(long pracId) {
		// TODO Auto-generated method stub
		Call<Object> call = teacherService.delPractical(pracId);
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

	void setAData(Assignment practical, int srNo2, VBox vBox, Pane homepane) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				srNo.setText(Integer.toString(srNo2));
				practicalName.setText(practical.getAssignmentName());
				practicalDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(practical.getAssignmentDate()));
				time.setText(practical.getTime());
				
				delBtn.setOnAction(e->{
					ButtonType foo = new ButtonType("Delete", ButtonData.OK_DONE);
					ButtonType bar = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					Alert alert = new Alert(AlertType.CONFIRMATION,
					        "This action will remove all data present in this assignment.\n"
					        +"Are you really want to delete assignment?",
					        foo,
					        bar);

					alert.setTitle("Delete Assignment?");
					DialogPane dialogPane = alert.getDialogPane();
					dialogPane.setHeaderText(null);
					dialogPane.setGraphic(null);
					dialogPane.getStylesheets().add(
							   getClass().getResource("css/home.css").toExternalForm());
							dialogPane.getStyleClass().add("dialog-pane"); 
					Optional<ButtonType> result = alert.showAndWait();

					if (result.orElse(bar) == foo) {
						//code to delete from api 
						deleteAssignment(practical.getAssignId());
						vBox.getChildren().remove(delBtn.getParent());
						System.out.println("delete subject id "+practical.getAssignId());
					}
				});
				popenbtn.setOnAction(e->{
//					try {
//						FXMLLoader loader = App.loadFXMLScreenLoader("editorscreen");
//						AnchorPane pane = loader.load();
//						((Pane) homepane.getParent()).getChildren().add(pane);
//						AnchorPane.setTopAnchor(pane, 0.0); 
//						AnchorPane.setLeftAnchor(pane, 0.0);
//						AnchorPane.setRightAnchor(pane, 0.0);
//						AnchorPane.setBottomAnchor(pane, 0.0);
//				    	((Pane) homepane.getParent()).getChildren().remove(homepane);
//						EditorController controller = loader.getController();
//						controller.setAData(practical);
//						
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				});
	}

	private void deleteAssignment(long assignId) {
		// TODO Auto-generated method stub
		Call<Object> call = teacherService.delAssignment(assignId);
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
