package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.utils.MasterHolder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrimaryController implements Initializable {
	
	@FXML
	Label welcomelab;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRootWithCss("loginsignup","application");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		MasterHolder masterHolder = MasterHolder.getInstanceHolder();
		Master master = masterHolder.getMaster();
		welcomelab.setText("welcome "+ master.getFirstName()+" "+master.getLastName());
	}
}
