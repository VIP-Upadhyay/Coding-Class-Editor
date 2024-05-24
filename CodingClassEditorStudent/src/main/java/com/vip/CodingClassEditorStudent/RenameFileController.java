package com.vip.CodingClassEditorStudent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorStudent.model.TreeFileModel;
import com.vip.CodingClassEditorStudent.services.CreateDirectory;
import com.vip.CodingClassEditorStudent.services.InputValidation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class RenameFileController implements Initializable{

	@FXML
	Button addNewFileBtn,cancelButton;
	@FXML
	TextField fileName;
	@FXML
	Label addFileError,fileNameerror;
	private boolean isAdded;
	TreeItem<TreeFileModel> treeItem;
	private TreeFileModel treeFileModel;
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
	public TreeFileModel getModel() {
		return treeFileModel;
	}
	
	@FXML
	public void onCancelButton() {
		Stage stage = (Stage)	cancelButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML 
	public void onAddNewFile() {
		if (InputValidation.validateFileName(fileName, fileNameerror)) {
			String output = CreateDirectory.renameFileForTreeFolder(treeItem.getValue().getId(),fileName.getText().trim());
			if (output.equals("File Already Exists !")) {
				addFileError.setText(output);
				addFileError.setVisible(true);
			}else {
				if (output.equals("Some thing went wrong")) {
					addFileError.setText(output);
					addFileError.setVisible(true);
				}else {
					addFileError.setVisible(false);
					File file = new File(output);
					treeFileModel = new TreeFileModel(file.getName(), file.getPath(),"FILE",EditorController.prettyFileSize(file.length()));
					isAdded=true;
					Stage stage = (Stage)	addNewFileBtn.getScene().getWindow();
					stage.close();
				}
			}
			
		}
		
	}

}
