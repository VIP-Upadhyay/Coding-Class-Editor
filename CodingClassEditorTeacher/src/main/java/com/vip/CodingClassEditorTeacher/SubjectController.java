package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Student;
import com.vip.CodingClassEditorTeacher.model.SubjectClass;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectController implements Initializable {
	
	@FXML
	Label subject,branch,semester;
	@FXML
	ScrollPane stbscrlPane;
	@FXML
	VBox stdvbox;
	@FXML
	Pane subjectpane;
	
	private SubjectClass subjectClass;
	int srNo=1;
	private boolean isViewUpdating=false;
	private int count=0;
	private TeacherService teacherService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("here");
		teacherService = RestApiUtils.getUserService();
	}
	
	public void setData(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
		subject.setText(subjectClass.getClassName());
		branch.setText(subjectClass.getBranch());
		semester.setText(subjectClass.getSemester());
		stdvbox.setSpacing(10);
		updateView();
		stbscrlPane.setOnScroll(e->{
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
		System.out.println(subjectClass.getClassName());
		Call<List<Student>> call = teacherService.getStudentList(subjectClass,count);
		call.enqueue(new Callback<List<Student>>() {
			
			@Override
			public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<Student> students = response.body();
					if (!students.isEmpty()) {
						for (Student student : students) {
							drawStudent(student);
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
			public void onFailure(Call<List<Student>> call, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}
		});
	}

	private void drawStudent(Student student) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {	
			@Override
			public void run() {
				try {		
					FXMLLoader fxmlLoader =  App.loadFXMLItems("studentitem");
					Pane pane = fxmlLoader.load();
					StudentListController itemController = fxmlLoader.getController();
					itemController.setData(student, srNo);
					stdvbox.getChildren().add(pane); 
					srNo++; 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			}
		});
		
	}

	@FXML
	public void onPracticalOpen() {
		try {
			FXMLLoader loader = App.loadFXMLScreenLoader("practicalscreen");
			AnchorPane pane = loader.load();
			((Pane) subjectpane.getParent()).getChildren().add(pane);
			AnchorPane.setTopAnchor(pane, 0.0); 
			AnchorPane.setLeftAnchor(pane, 0.0);
			AnchorPane.setRightAnchor(pane, 0.0);
			AnchorPane.setBottomAnchor(pane, 0.0); 
	    	((Pane) subjectpane.getParent()).getChildren().remove(subjectpane); 
			PracticalScreenController scontroller = loader.getController();
			scontroller.setData(subjectClass);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@FXML
	public void onAssignmentOpen() {
		try {
			FXMLLoader loader = App.loadFXMLScreenLoader("assignmentscreen");
			AnchorPane pane = loader.load();
			((Pane) subjectpane.getParent()).getChildren().add(pane);
			AnchorPane.setTopAnchor(pane, 0.0); 
			AnchorPane.setLeftAnchor(pane, 0.0);
			AnchorPane.setRightAnchor(pane, 0.0);
			AnchorPane.setBottomAnchor(pane, 0.0); 
	    	((Pane) subjectpane.getParent()).getChildren().remove(subjectpane); 
			AssignmentScreenController scontroller = loader.getController();
			scontroller.setData(subjectClass);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
