package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import com.vip.CodingClassEditorTeacher.model.Assignment;
import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.model.Practical;
import com.vip.CodingClassEditorTeacher.model.Student;
import com.vip.CodingClassEditorTeacher.model.SubjectClass;
import com.vip.CodingClassEditorTeacher.service.TeacherService;
import com.vip.CodingClassEditorTeacher.service.WebSocketExp;
import com.vip.CodingClassEditorTeacher.utils.MasterHolder;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;
import com.vip.CodingClassEditorTeacher.utils.WebSocketHolder;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import okhttp3.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorController implements Initializable {

	@FXML
	Label praclab,receiver;
	@FXML
	WebView editor,console;
	
	@FXML
	AnchorPane terminalPane;
	
	@FXML 
	TabPane tabPane;

	@FXML
	VBox stdvbox,pmsgvbox;
	
	@FXML
	ScrollPane stbscrlPane,pmsgScrlPane;
	
	@FXML
	TextArea pmsgTextArea;
	
	@FXML
	Button pmsgSend;
	
	private SubjectClass subjectClass;
	private boolean isViewUpdating=false;
	private int count=0;
	private TeacherService teacherService;
	
	private JSObject javascriptConnector;
	private String fileDir="";
    private JavaConnector javaConnector = new JavaConnector();
    private WebSocket wSocket;
    private WebSocketExp webSocketExp;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
		teacherService = RestApiUtils.getUserService();
		WebSocketHolder webSocketHolder = WebSocketHolder.getInstanceHolder();
		wSocket = webSocketHolder.getwSocket();
		webSocketExp = webSocketHolder.getWebSocketExp();
		webSocketExp.setwSocket(wSocket);
		webSocketExp.setReceiver(receiver);
		webSocketExp.setpBox(pmsgvbox);
		webSocketExp.setPscrlPane(pmsgScrlPane);
		webSocketExp.setpTextArea(pmsgTextArea);
		webSocketExp.setpButton(pmsgSend);
		pmsgvbox.setSpacing(5);
		pmsgSend.setGraphic(App.loadGraphics("img/send.png", 25));
		pmsgvbox.heightProperty().addListener(obs->{
			pmsgScrlPane.setVvalue(1D);
		});
		WebEngine webEngine = editor.getEngine();
		webEngine.setJavaScriptEnabled(true);
		webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

                // get the Javascript connector object. 
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
                webSocketExp.setJavascriptConnector(javascriptConnector);
            }
        });
		webEngine.load(getClass().getResource("ace/editor.html").toExternalForm());
		webSocketExp.setWebEngine(console.getEngine());
		
	}
	public void setData(Practical practical) {
		this.subjectClass = practical.getSubjectClass();
		praclab.setText(practical.getPracticalName());
		MasterHolder holder = MasterHolder.getInstanceHolder();
		Master master = holder.getMaster();
		System.out.println(master.getEmail());
		
		stdvbox.setSpacing(5);
		updateView();
		stbscrlPane.setOnScroll(e->{
			if(e.getDeltaY()<0) {
				System.out.println("bottom");
				if(!isViewUpdating) {
					isViewUpdating=true; 
					updateView();  
				}else {
					System.out.println("still updating");
				}
			}
		});
	}
	
	private void updateView() {
		// TODO Auto-generated method stub
		System.out.println(subjectClass.getClassName());
		Call<List<Student>> call = teacherService.getStudentList(subjectClass,count);
		call.enqueue(new Callback<List<Student>>() {
			
			@Override
			public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<Student> students = response.body();
					if (!students.isEmpty()) {
						for (Student student : students) {
							drawStudent(student);
						}
						count++;
						isViewUpdating=false;
					}else {
						System.out.println("its empty");
						isViewUpdating=false;
					}
				}else {
					try {
						System.out.println(response.errorBody().string().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void onFailure(Call<List<Student>> call, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}
		});
	}

	private void drawStudent(Student student) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {	
			@Override
			public void run() {
				try {	
					FXMLLoader fxmlLoader =  App.loadFXMLItems("stditem");
					Pane pane = fxmlLoader.load();
					StdListController itemController = fxmlLoader.getController();
					itemController.setData(student,webSocketExp);
					stdvbox.getChildren().add(pane);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			}
		});
		
	}

	
	public class JavaConnector {
	    /**
	     * called when the JS side wants a String to be converted.
	     *
	     * @param value
	     *         the String to convert
	     */

		public void code(String value) {
	        if (null != value) {
	        	try {
					if (!fileDir.equals("")) {
						Files.write(Paths.get(fileDir), value.getBytes());
						if (wSocket!=null) {
							wSocket.send(value);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
	        }
	    }
	}


	public void setAData(Assignment practical) {
		// TODO Auto-generated method stub
		this.subjectClass = practical.getSubjectClass();
		praclab.setText(practical.getAssignmentName());
		MasterHolder holder = MasterHolder.getInstanceHolder();
		Master master = holder.getMaster();
		System.out.println(master.getEmail());
		
		stdvbox.setSpacing(5);
		updateView();
		stbscrlPane.setOnScroll(e->{
			if(e.getDeltaY()<0) {
				System.out.println("bottom");
				if(!isViewUpdating) {
					isViewUpdating=true; 
					updateView();  
				}else {
					System.out.println("still updating");
				}
			}
		});
	}
	
}
