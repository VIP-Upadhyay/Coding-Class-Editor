package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.SubjectClass;
import com.vip.CodingClassEditorTeacher.service.InputValidation;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubjectController implements Initializable {
	
	@FXML
	private Label addClassError,subjectNameError,semestererror,brancherror;

	@FXML
	private Button addClassButton;
	
	@FXML
	private TextField subjectName;
	
	@FXML
	private ComboBox<String> semester,branch;
	
	private TeacherService teacherService;
	
	private boolean isAdded=false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		teacherService = RestApiUtils.getUserService();
		// TODO Auto-generated method stub
		ObservableList<String> branchlist = FXCollections.observableArrayList("Computer Science Engineering","Computer Engineering",
				"Information Technology","Artificial Intelligence");
		ObservableList<String> semesterlist = FXCollections.observableArrayList("1st sem","2nd sem","3rd sem","4th sem","5th sem","6th sem","7th sem","8th sem");
		semester.setItems(semesterlist);
		branch.setItems(branchlist);
	}

	void setProgress() {
		System.out.println("adding progress ");
	}
	@FXML
	protected void addClassButtonAction() {
		if (InputValidation.validateAddClass(subjectName, subjectNameError, semester, semestererror, branch, brancherror)) {
			SubjectClass subjectClass = new SubjectClass();
			subjectClass.setClassName(subjectName.getText().trim());
			subjectClass.setSemester(semester.getValue().toString().trim());
			subjectClass.setBranch(branch.getValue().toString().trim());
			addClasses(subjectClass);
		}
	}
	
	private void addClasses(SubjectClass subjectClass) {
		// TODO Auto-generated method stub
		 //progressBar.setProgress(0.25);
		 Call<Object> call = teacherService.addClass(subjectClass);
		 //progressBar.setProgress(0.75);
		 call.enqueue(new Callback<Object>() {

			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
				//	progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							if (response.body().toString().contains("class added sucessfully")) {
								addClassError.setVisible(false);
								//here to close window
								Stage stage = (Stage)	addClassButton.getScene().getWindow();
								stage.close();
								isAdded=true;
							}else {
								if (response.body().toString().contains("class already exists")) {
									addClassError.setText("class already exists");
									addClassError.setVisible(true);
								}else {
									addClassError.setText("something went wrong. try againg later");
									addClassError.setVisible(true);
								}
							}
							//progressBar.setProgress(0);
						}
					});
					
				}else {
					//progressBar.setProgress(1.0);
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								try {
									addClassError.setText(response.errorBody().string());
									addClassError.setVisible(true);
									System.out.println(response.errorBody().string());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//progressBar.setProgress(0);
							}
						});
				}
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				//progressBar.setProgress(100);
				//progressBar.setProgress(0);
				t.printStackTrace();
			}
		});
	}
	
	public boolean isSuccessfullyAdded() {
		return isAdded;
	}
}
