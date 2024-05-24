package com.vip.CodingClassEditorStudent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.TreeFileModel;
import com.vip.CodingClassEditorStudent.services.CreateDirectory;
import com.vip.CodingClassEditorStudent.services.InputValidation;
import com.vip.CodingClassEditorStudent.services.LanguageGetter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class AddNewFileController implements Initializable{
	
	@FXML
	Button addNewFileBtn,cancelButton;
	@FXML
	TextField fileName;
	@FXML
	Label addFileError,fileNameerror;
	private boolean isAdded;
	TreeItem<TreeFileModel> treeItem;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setData(TreeItem<TreeFileModel> treeItem) {
		// TODO Auto-generated method stub
		this.treeItem = treeItem;
	}

	public boolean isSuccessfullyAdded() {
		// TODO Auto-generated method stub
		return isAdded;
	}
	
	@FXML
	public void onCancelButton() {
		Stage stage = (Stage)	cancelButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML 
	public void onAddNewFile() {
		if (InputValidation.validateFileName(fileName, fileNameerror)) {
			String output = CreateDirectory.createFileForTreeFolder(treeItem.getValue().getId(),fileName.getText().trim());
			if (output.equals("File Already Exists !")) {
				addFileError.setText(output);
				addFileError.setVisible(true);
			}else {
				if (output.equals("Some thing went wrong")) {
					addFileError.setText(output);
					addFileError.setVisible(true);
				}else {
					addFileError.setVisible(false);
					try {
						Files.write(Paths.get(output),
								LanguageGetter.getHelloCodeForLanguage(fileName.getText().trim()).getBytes());
						File file = new File(output);
						TreeItem<TreeFileModel> ti = new TreeItem<>(new TreeFileModel(file.getName(), file.getPath(),"FILE",EditorController.prettyFileSize(file.length())));
						treeItem.getChildren().add(ti);
						isAdded=true;
						Stage stage = (Stage)	addNewFileBtn.getScene().getWindow();
						stage.close(); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					}
				}
			}
			
		}
		
	}
}
