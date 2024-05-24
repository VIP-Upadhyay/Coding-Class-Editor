package com.vip.CodingClassEditorStudent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.GlobeMessage;
import com.vip.CodingClassEditorStudent.model.PrivateMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class MGMController implements Initializable {
	@FXML
	Label name,msg,date;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setData(GlobeMessage message) {
		name.setText(message.getName()+" ("+message.getUsername()+")");
		name.setAlignment(Pos.BASELINE_RIGHT);
		msg.setText(message.getMessage());
		msg.setAlignment(Pos.BASELINE_RIGHT);
		String dString = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(message.getDate());
		date.setText(dString);
		date.setAlignment(Pos.BASELINE_RIGHT);
	}
	
	public void setDataPM(PrivateMessage message) {
		name.setVisible(false);
		msg.setText(message.getMessage());
		msg.setAlignment(Pos.BASELINE_RIGHT);
		String dString = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(message.getDate());
		date.setText(dString);
		date.setAlignment(Pos.BASELINE_RIGHT);
	}
}
