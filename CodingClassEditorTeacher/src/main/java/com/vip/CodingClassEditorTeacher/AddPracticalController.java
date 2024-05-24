package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Practical;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPracticalController implements Initializable{
	
	@FXML
	Label addPracticalError,practicalnameerror,dateerror,timeerror;
	
	@FXML
	DatePicker practicalDate;
	
	@FXML
	Button addPracticalButton;
	
	@FXML
	private ComboBox<String> time;
	
	@FXML
	TextArea practicalName;
	
	private TeacherService teacherService;
	
	private SubjectClass subjectClass;
	private boolean isAdded=false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		teacherService = RestApiUtils.getUserService();
		ObservableList<String> timelist = FXCollections.observableArrayList("15 min","30 min",
				"1 hr","1:15 hr","1:30 hr","1:45 hr","2 hr");
		time.setItems(timelist);
	}
	public void setData(SubjectClass subjectClass) {
		this.subjectClass =subjectClass;
		System.out.println("subject class "+subjectClass.getSubId());
	}
	
	@FXML
	public void addPracticalButtonAction() {
		if (InputValidation.validateAddPractical(practicalName, practicalnameerror, time, timeerror, practicalDate, dateerror)) {
			Practical practical = new Practical();
			practical.setPracticalName(practicalName.getText().trim());
			LocalDate ld = practicalDate.getValue();
			Calendar c =  Calendar.getInstance();
			c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
			Date date = c.getTime();
			practical.setPracticalDate(date);
			practical.setTime(time.getValue().toString().trim());
			practical.setSubjectClass(subjectClass);
			addPractical(practical);
		}
	}
	
	private void addPractical(Practical practical) {
		// TODO Auto-generated method stub
		Call<Object> call = teacherService.addPractical(practical);
		call.enqueue(new Callback<Object>() {

			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if (response.body().toString().contains("practical added sucessfully")) {
								addPracticalError.setVisible(false);
								//here to close window
								Stage stage = (Stage)	addPracticalButton.getScene().getWindow();
								stage.close();
								isAdded=true;
							}else {
								if (response.body().toString().contains("practical already exists")) {
									addPracticalError.setText("practical already exists");
									addPracticalError.setVisible(true);
								}else {
									addPracticalError.setText("something went wrong. try againg later");
									addPracticalError.setVisible(true);
								}
							}
						}
					});
				}else {
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							try {
								addPracticalError.setText(response.errorBody().string());
								addPracticalError.setVisible(true);
								System.out.println(response.errorBody().string());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public boolean isSuccessfullyAdded() {
		return isAdded;
	}
}
