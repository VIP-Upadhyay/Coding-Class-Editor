package com.vip.CodingClassEditorStudent;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.Assignment;
import com.vip.CodingClassEditorStudent.model.Practical;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PracticalListController implements Initializable{
	@FXML
	Label srNo,practicalName,practicalDate,time;
	
	@FXML
	Button popenbtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	void setData(Practical practical, int srNo2, VBox vBox,Pane homepane) {
		// TODO Auto-generated method stub
		srNo.setText(Integer.toString(srNo2));
		practicalName.setText(practical.getPracticalName());
		practicalDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(practical.getPracticalDate()));
		time.setText(practical.getTime());
		
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

	@FXML
	public void onPracticalOpen() {
		
	}

	public void setAData(Assignment practical, int srNo2, VBox vBox, Pane homepane) {
		// TODO Auto-generated method stub
		srNo.setText(Integer.toString(srNo2));
		practicalName.setText(practical.getAssignmentName());
		practicalDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(practical.getAssignmentDate()));
		time.setText(practical.getTime());
		
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
//				controller.setAData(practical);
//				
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		});
	}
	
}
