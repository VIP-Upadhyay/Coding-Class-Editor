package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.SubjectClass;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SubjectController implements Initializable {
	
	@FXML
	Label subject,branch,semester;
	
	@FXML
	Pane subjectpane;
	
	private SubjectClass subjectClass;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void setData(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
		subject.setText(subjectClass.getClassName());
		branch.setText(subjectClass.getBranch());
		semester.setText(subjectClass.getSemester());
		
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
