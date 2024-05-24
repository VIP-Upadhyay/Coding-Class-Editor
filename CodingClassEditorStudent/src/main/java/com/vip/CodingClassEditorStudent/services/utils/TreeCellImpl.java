package com.vip.CodingClassEditorStudent.services.utils;

import java.io.IOException;
import java.util.Optional;

import com.vip.CodingClassEditorStudent.AddNewFileController;
import com.vip.CodingClassEditorStudent.AddNewFolderController;
import com.vip.CodingClassEditorStudent.App;
import com.vip.CodingClassEditorStudent.RenameFileController;
import com.vip.CodingClassEditorStudent.RenameFolderController;
import com.vip.CodingClassEditorStudent.model.TreeFileModel;
import com.vip.CodingClassEditorStudent.services.CreateDirectory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class TreeCellImpl extends TreeCell<TreeFileModel> {
	 
    private final ContextMenu foldermenu1 = new ContextMenu();
    private final ContextMenu foldermenu = new ContextMenu();
    private final ContextMenu filemenu = new ContextMenu();
    public TreeCellImpl() {
    	MenuItem addfile = new MenuItem("add new File");
    	MenuItem addfolder = new MenuItem("add new Folder");
    	MenuItem deletefolder = new MenuItem("delete Folder");
    	MenuItem rename = new MenuItem("rename");
    	foldermenu.getItems().add(addfile);
    	foldermenu.getItems().add(addfolder);
    	foldermenu.getItems().add(deletefolder);
    	foldermenu.getItems().add(rename);
    	addfile.setOnAction(e->{
    		addNewFile();
    	});
    	addfolder.setOnAction(e->{
    		addNewFolder();
    	});
    	deletefolder.setOnAction(e->{
    		deleteFolder();
    	});
    	rename.setOnAction(e->{
    		RenameFolder();
    	});
    	MenuItem deletefile = new MenuItem("delete File");
    	MenuItem rename1 = new MenuItem("rename");
    	filemenu.getItems().add(deletefile);
    	filemenu.getItems().add(rename1);
    	deletefile.setOnAction(e->{
    		deleteFile();
    	});
    	rename1.setOnAction(e->{
    		renameFile();
    	});
    	MenuItem addfile1 = new MenuItem("add new File");
    	MenuItem addfolder1 = new MenuItem("add new Folder");
    	foldermenu1.getItems().add(addfile1); 
    	foldermenu1.getItems().add(addfolder1);
    	addfile1.setOnAction(e->{
    		addNewFile();
    	});
    	addfolder1.setOnAction(e->{
    		addNewFolder();
    	});
    }

	

	@Override
    public void startEdit() {
        super.startEdit();

    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem().getName());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(TreeFileModel item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (!isEditing()) {
            	setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (getIndex()!=0) {
                	if (getItem().getType().equals("FOLDER")) {
                		
                		setContextMenu(foldermenu);
            		}else {
            			if (getItem().getType().equals("FILE")) {
            	        	setContextMenu(filemenu);
            			}
            		}
				}else {
					if (getItem().getType().equals("FOLDER")) {
                		setContextMenu(foldermenu1);
            		}
				}
            }
        }
    }
    
    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
    
    
    private void addNewFile() {
		// TODO Auto-generated method stub
    	try {
			FXMLLoader fxmlLoader = App.loadFXMLItems("addnewfile");
			Parent parent = fxmlLoader.load();
			AddNewFileController controller = (AddNewFileController) fxmlLoader.getController();
			controller.setData(getTreeItem());
			Scene scene = new Scene(parent, 348, 173);
			scene.getStylesheets().add(App.class.getResource("css/"+"application" + ".css").toExternalForm());
			Stage stage = new Stage(); 
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		    if (controller.isSuccessfullyAdded()) {
		    	TreeFileModel tfm= getItem();
		    	tfm.setNoOfFiles(tfm.getNoOfFiles()+1);
		    	commitEdit(tfm);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void addNewFolder() {
		// TODO Auto-generated method stub
    	try {
			FXMLLoader fxmlLoader = App.loadFXMLItems("addnewfolder");
			Parent parent = fxmlLoader.load();
			AddNewFolderController controller = (AddNewFolderController) fxmlLoader.getController();
			controller.setData(getTreeItem());
			Scene scene = new Scene(parent, 348, 173);
			scene.getStylesheets().add(App.class.getResource("css/"+"application" + ".css").toExternalForm());
			Stage stage = new Stage(); 
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		    if (controller.isSuccessfullyAdded()) {
		    	TreeFileModel tfm= getItem();
		    	tfm.setNoOfFiles(tfm.getNoOfFiles()+1);
		    	commitEdit(tfm);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private void renameFile() {
		// TODO Auto-generated method stub
    	try {
			FXMLLoader fxmlLoader = App.loadFXMLItems("renamefile");
			Parent parent = fxmlLoader.load();
			RenameFileController controller = (RenameFileController) fxmlLoader.getController();
			controller.setData(getTreeItem());
			Scene scene = new Scene(parent, 348, 173);
			scene.getStylesheets().add(App.class.getResource("css/"+"application" + ".css").toExternalForm());
			Stage stage = new Stage(); 
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		    if (controller.isSuccessfullyAdded()) {
		    	getTreeItem().setValue(controller.getModel());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void RenameFolder() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader fxmlLoader = App.loadFXMLItems("renamefolder");
			Parent parent = fxmlLoader.load();
			RenameFolderController controller = (RenameFolderController) fxmlLoader.getController();
			controller.setData(getTreeItem());
			Scene scene = new Scene(parent, 348, 173);
			scene.getStylesheets().add(App.class.getResource("css/"+"application" + ".css").toExternalForm());
			Stage stage = new Stage(); 
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		    if (controller.isSuccessfullyAdded()) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteFolder() {
		// TODO Auto-generated method stub
		ButtonType foo = new ButtonType("Delete", ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.CONFIRMATION,
		        "This action will remove all data present in this "+getItem().getName()+".\n"
		        +"Are you really want to delete "+getItem().getName()+"?",
		        foo,
		        bar);

		alert.setTitle("Delete "+getItem().getName()+"?");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setHeaderText(null);
		dialogPane.setGraphic(null);
		dialogPane.getStylesheets().add(
				App.class.getResource("css/"+"home" + ".css").toExternalForm());
				dialogPane.getStyleClass().add("dialog-pane"); 
		Optional<ButtonType> result = alert.showAndWait();

		if (result.orElse(bar) == foo) {
			//code to delete from api 
			if (CreateDirectory.deleteFolderForTreeFolder(getItem().getId())) {
				if (getTreeItem().getParent()!=null) {
					TreeFileModel tmf =  getTreeItem().getParent().getValue();
					tmf.setNoOfFiles(tmf.getNoOfFiles()-1);
					getTreeItem().getParent().setValue(tmf);
				}
				getTreeItem().getParent().getChildren().remove(getTreeItem());
				
			}
		}
	}

	private void deleteFile() {
		// TODO Auto-generated method stub
		ButtonType foo = new ButtonType("Delete", ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.CONFIRMATION,
		        "This action will remove all data present in this "+getItem().getName()+".\n"
		        +"Are you really want to delete "+getItem().getName()+"?",
		        foo,
		        bar);

		alert.setTitle("Delete "+getItem().getName()+"?");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setHeaderText(null);
		dialogPane.setGraphic(null);
		dialogPane.getStylesheets().add(
				App.class.getResource("css/"+"home" + ".css").toExternalForm());
				dialogPane.getStyleClass().add("dialog-pane");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.orElse(bar) == foo) {
			//code to delete from api 
			if (CreateDirectory.deleteFileForTreeFolder(getItem().getId())) {
				if (getTreeItem().getParent()!=null) {
					TreeFileModel tmf =  getTreeItem().getParent().getValue();
					tmf.setNoOfFiles(tmf.getNoOfFiles()-1);
					getTreeItem().getParent().setValue(tmf);
				}
				getTreeItem().getParent().getChildren().remove(getTreeItem());
			}
		}
	}
}