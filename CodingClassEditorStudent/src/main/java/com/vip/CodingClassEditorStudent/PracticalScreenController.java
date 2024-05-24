package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.Practical;
import com.vip.CodingClassEditorStudent.model.SubjectClass;
import com.vip.CodingClassEditorStudent.services.StudentService;
import com.vip.CodingClassEditorStudent.services.utils.RestApiUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PracticalScreenController implements Initializable {

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
	private StudentService studentService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		studentService = RestApiUtils.getUserService();
	}
	public void setData(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
		sbjprac.setText(subjectClass.getClassName()+" Practicals");
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
		Call<List<Practical>> call = studentService.getPracticalList(subjectClass.getSubId(), count);
		call.enqueue(new Callback<List<Practical>>() {
			
			@Override
			public void onResponse(Call<List<Practical>> call, Response<List<Practical>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<Practical> practicals = response.body();
					if (!practicals.isEmpty()) {
						for (Practical practical : practicals) {
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
						System.out.println(response.errorBody().string().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Practical>> call, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}
		});
	}
	
	private void drawStudent(Practical practical) {
		// TODO Auto-generated method stub
		practical.setSubjectClass(subjectClass);
		Platform.runLater(new Runnable() {	
			@Override
			public void run() {
				try {		
					FXMLLoader fxmlLoader =  App.loadFXMLItems("practicalitem");
					Pane pane = fxmlLoader.load();
					PracticalListController itemController = fxmlLoader.getController();
					itemController.setData(practical, srNo,pracvbox,practicalpane);
					pracvbox.getChildren().add(pane); 
					srNo++; 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			}
		});
	}
	
}
