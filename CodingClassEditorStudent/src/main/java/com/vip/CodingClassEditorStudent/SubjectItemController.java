package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.SubjectClass;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SubjectItemController implements Initializable {
	
	@FXML
	Button openBtnBtn;
	
	@FXML
	Label subject,semester,branch;
	
	@FXML
	Pane subPane;
	
	//private StudentService studentService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//studentService = RestApiUtils.getUserService();
	}
	
	void setData(SubjectClass subjectClass,VBox vBox,Pane homepane) {

		subject.setText(subjectClass.getClassName());
		semester.setText(subjectClass.getSemester());
		branch.setText(subjectClass.getBranch()); 
		 
		
		
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



}
