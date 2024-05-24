package com.vip.CodingClassEditorStudent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.vip.CodingClassEditorStudent.model.Assignment;
import com.vip.CodingClassEditorStudent.model.CodeMessage;
import com.vip.CodingClassEditorStudent.model.ConsoleMessage;
import com.vip.CodingClassEditorStudent.model.Master;
import com.vip.CodingClassEditorStudent.model.Practical;
import com.vip.CodingClassEditorStudent.model.PrivateMessage;
import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.model.TreeFileModel;
import com.vip.CodingClassEditorStudent.model.Message.MessageType;
import com.vip.CodingClassEditorStudent.model.MessageDetails;
import com.vip.CodingClassEditorStudent.services.CreateDirectory;
import com.vip.CodingClassEditorStudent.services.InputValidation;
import com.vip.CodingClassEditorStudent.services.LanguageGetter;
import com.vip.CodingClassEditorStudent.services.StudentService;
import com.vip.CodingClassEditorStudent.services.WebSocketExp;
import com.vip.CodingClassEditorStudent.services.utils.RestApiUtils;
import com.vip.CodingClassEditorStudent.services.utils.StudentHolder;
import com.vip.CodingClassEditorStudent.services.utils.TreeCellImpl;
import com.vip.CodingClassEditorStudent.services.utils.WebSocketHolder;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	WebView editor;
	
	@FXML
	AnchorPane terminalPane;
	
	@FXML 
	TabPane tabPane;
	
	@FXML
	VBox pmsgvbox;
	
	@FXML
	ScrollPane pmsgScrlPane;
	
	@FXML
	TextArea pmsgTextArea;
	
	@FXML
	Button pmsgSend;
	
	@FXML
	TreeView<TreeFileModel> fileTree;
	private JSObject javascriptConnector;
	private String dir;
	private String fileDir="";
    private JavaConnector javaConnector = new JavaConnector();
    private WebSocket wSocket;
    private WebSocketExp webSocketExp;
    Student student;
    private Master master;
    private StudentService studentService;
    String language="java";
    private boolean isViewUpdating=false;
	private int count=0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		studentService = RestApiUtils.getUserService();
		//above code want to remove
		WebSocketHolder webSocketHolder = WebSocketHolder.getInstanceHolder();
		wSocket = webSocketHolder.getwSocket();
		webSocketExp = webSocketHolder.getWebSocketExp();
		webSocketExp.setwSocket(wSocket);
		webSocketExp.setPmsgvbox(pmsgvbox);
		pmsgvbox.setSpacing(5);
		pmsgvbox.heightProperty().addListener(obs->{
			pmsgScrlPane.setVvalue(1D);
		});
		pmsgSend.setGraphic(App.loadGraphics("img/send.png", 25));
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
		
		
	}
	private void updateView() {
		// TODO Auto-generated method stub
		MessageDetails messageDetails = new MessageDetails(master.getUserId(), student.getUserId());
		Call<List<PrivateMessage>> call = studentService.getMessages(messageDetails, count);
		call.enqueue(new Callback<List<PrivateMessage>>() {
			@Override
			public void onResponse(Call<List<PrivateMessage>> call,
					retrofit2.Response<List<PrivateMessage>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					List<PrivateMessage> messages = response.body();
					if (!messages.isEmpty()) {
						for (PrivateMessage message : messages) {
							Platform.runLater(new Runnable() {	
								@Override
								public void run() {
									webSocketExp.addPrivateMessage(message);
								}
							});
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
			public void onFailure(Call<List<PrivateMessage>> call, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}
		});
	}
	public void setData(Practical practical) {
		praclab.setText(practical.getPracticalName());
		StudentHolder holder = StudentHolder.getInstanceHolder();
		student = holder.getStudent();
		FindMasterId(practical.getSubjectClass().getSubId());
		dir = CreateDirectory.createPracticalDirectory(student.getUserName(), practical.getSubjectClass().getClassName(), practical.getPracId());
		System.out.println(dir);
		File file = new File(dir);
		if (file.isDirectory()) {
			fileTree.setRoot(getNodesForDirectory(file)); 
			fileTree.setEditable(true);
			fileTree.setCellFactory((TreeView<TreeFileModel> f)-> new TreeCellImpl());
		}else {
			
			System.out.println("here "+file.listFiles().length);
		}
		TerminalBuilder terminalBuilder = new TerminalBuilder();
		terminalBuilder.setTerminalPath(Path.of(dir));
		TerminalTab terminalTab = terminalBuilder.newTerminal();
		terminalTab.setClosable(false);
		tabPane.getTabs().add(terminalTab);
		webSocketExp.setTerminalTab(terminalTab);
		terminalTab.getTerminal().onTerminalFxReady(()->{
			
			 terminalTab.getTerminal().setOnKeyPressed(e->{
				 WebView webView = (WebView) terminalTab.getTerminal().getChildren().get(0);
				 WebEngine engine = webView.getEngine();
				 String text = engine.executeScript(
						 "document.getElementById('terminal').getElementsByTagName('iframe')[0].contentWindow.document.body.parentElement.innerHTML"
						 ).toString();
				 ConsoleMessage consoleMessage = new ConsoleMessage();
				 consoleMessage.setSenderId(student.getUserId());
				 consoleMessage.setReciverId(master.getUserId());
				 consoleMessage.setConsole(text);
				 consoleMessage.setMessageType(MessageType.CODE);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString;
				try {
					jsonString = objectMapper.writeValueAsString(consoleMessage);
					wSocket.send(jsonString);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 });
			
		});
		receiver.setText(master.getFirstName()+" "+master.getLastName()+" ("+master.getUserName()+")");
		updateView();
		pmsgScrlPane.setOnScroll(e->{
			if(e.getDeltaY()>0) {
				System.out.println("TOP");
				if(!isViewUpdating) {
					isViewUpdating=true; 
					updateView();  
				}else {
					System.out.println("still updating");
				}
			}
		});
		pmsgSend.setOnAction(e->{
			if (master!=null) {
				if (!pmsgTextArea.getText().trim().equals("")) {
					PrivateMessage message = new PrivateMessage();
					message.setDate(new Date());
					message.setMessageType(MessageType.GLOBE);
					message.setSenderId(student.getUserId());
					message.setReceiverId(master.getUserId());
					message.setMessage(pmsgTextArea.getText());
					try {
						ObjectMapper mapper = new ObjectMapper();
						String jsonString = mapper.writeValueAsString(message);
						wSocket.send(jsonString);
						pmsgTextArea.setText("");
					} catch (Exception ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				}
			}
		});
	}
	
	
	public static TreeItem<TreeFileModel> getNodesForDirectory(File directory) {
        // For every iteration, this will extract the number of items
        // (files and directories) in the current node
        long numberOfFiles = Objects.requireNonNull(directory.listFiles()).length;

        // The directory/folder name will include the number of files it contains
        TreeFileModel fileModel = new TreeFileModel(directory.getName(), directory.getPath(), "FOLDER",numberOfFiles);
        ImageView imageView = new ImageView(App.loadImage("img/folder.png"));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        TreeItem<TreeFileModel> root =  new TreeItem<TreeFileModel>(fileModel, imageView);

        File[] listFiles = directory.listFiles();

        for (int i = 0; i < Objects.requireNonNull(listFiles).length; i++) {
            File f = listFiles[i];

            if (f.isDirectory()) {
                root.getChildren().add(getNodesForDirectory(f));
                
            } else {
                // Add the node with the corresponding file size
                if (InputValidation.validateFileNameForCell(f.getName())) {
                	TreeFileModel fileModels = new TreeFileModel(f.getName(), f.getPath(), "FILE",prettyFileSize(f.length()));
                    root.getChildren().add(new TreeItem<TreeFileModel>(fileModels));
				}
            }
        }

        // Sort the nodes in descending alphabetical order.
        //root.getChildren().sort(Comparator.comparing(TreeItem::getValue));

        return root;
    }
	
	public static String prettyFileSize(long size) {
        if (size <= 0) {
            return "0 B";
        }

        final String[] units = new String[]{"B", "KiB", "MiB", "GiB", "TiB"};

        int sizegroup = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, sizegroup)) + " " + units[sizegroup];
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
							try {
								CodeMessage codeMessage = new CodeMessage();
								codeMessage.setSenderId(student.getUserId());
								codeMessage.setReciverId(master.getUserId());
								codeMessage.setCode(value);
								codeMessage.setMessageType(MessageType.CODE);
								codeMessage.setLanguage(language);
								ObjectMapper objectMapper = new ObjectMapper();
								String jsonString = objectMapper.writeValueAsString(codeMessage);
								wSocket.send(jsonString);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
	        }
	    }
	}
	
	@FXML
	public void itemSelect() {
		TreeItem<TreeFileModel> treeItem = fileTree.getSelectionModel().getSelectedItem();
		if (treeItem!=null) {
			TreeFileModel treeFileModel = treeItem.getValue();
			if (treeFileModel.getType()=="FILE") {
				fileDir = treeFileModel.getId();
				Path fileName = Path.of(fileDir);
				language = LanguageGetter.getLanguage(treeFileModel.getName());
				String code;
				try {
					code = Files.readString(fileName);
					javascriptConnector.call("showCode", code,language);
				} catch (IOException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private void FindMasterId(long subId) {
		// TODO Auto-generated method stub
		Call<Object> call = studentService.getMaster(subId);
		call.enqueue(new Callback<Object>() {
			
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					ObjectMapper objectMapper = new ObjectMapper();
					master = objectMapper.convertValue(response.body(), Master.class);
					webSocketExp.setMaster(master);
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
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("exception....here....");
			}
		});
	}
	public void setAData(Assignment practical) {
		// TODO Auto-generated method stub
		praclab.setText(practical.getAssignmentName());
		StudentHolder holder = StudentHolder.getInstanceHolder();
		student = holder.getStudent();
		FindMasterId(practical.getSubjectClass().getSubId());
		dir = CreateDirectory.createAssignmentDirectory(student.getUserName(), practical.getSubjectClass().getClassName(), practical.getAssignId());
		System.out.println(dir);
		File file = new File(dir);
		if (file.isDirectory()) {
			fileTree.setRoot(getNodesForDirectory(file)); 
			fileTree.setEditable(true);
			fileTree.setCellFactory((TreeView<TreeFileModel> f)-> new TreeCellImpl());
		}else {
			
			System.out.println("here "+file.listFiles().length);
		}
		TerminalBuilder terminalBuilder = new TerminalBuilder();
		terminalBuilder.setTerminalPath(Path.of(dir));
		TerminalTab terminalTab = terminalBuilder.newTerminal();
		terminalTab.setClosable(false);
		tabPane.getTabs().add(terminalTab);
		webSocketExp.setTerminalTab(terminalTab);
		terminalTab.getTerminal().onTerminalFxReady(()->{
			
			 terminalTab.getTerminal().setOnKeyPressed(e->{
				 WebView webView = (WebView) terminalTab.getTerminal().getChildren().get(0);
				 WebEngine engine = webView.getEngine();
				 String text = engine.executeScript(
						 "document.getElementById('terminal').getElementsByTagName('iframe')[0].contentWindow.document.body.parentElement.innerHTML"
						 ).toString();
				 ConsoleMessage consoleMessage = new ConsoleMessage();
				 consoleMessage.setSenderId(student.getUserId());
				 consoleMessage.setReciverId(master.getUserId());
				 consoleMessage.setConsole(text);
				 consoleMessage.setMessageType(MessageType.CODE);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString;
				try {
					jsonString = objectMapper.writeValueAsString(consoleMessage);
					wSocket.send(jsonString);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 });
			
		});
		receiver.setText(master.getFirstName()+" "+master.getLastName()+" ("+master.getUserName()+")");
		updateView();
		pmsgScrlPane.setOnScroll(e->{
			if(e.getDeltaY()>0) {
				System.out.println("TOP");
				if(!isViewUpdating) {
					isViewUpdating=true; 
					updateView();  
				}else {
					System.out.println("still updating");
				}
			}
		});
		pmsgSend.setOnAction(e->{
			if (master!=null) {
				if (!pmsgTextArea.getText().trim().equals("")) {
					PrivateMessage message = new PrivateMessage();
					message.setDate(new Date());
					message.setMessageType(MessageType.GLOBE);
					message.setSenderId(student.getUserId());
					message.setReceiverId(master.getUserId());
					message.setMessage(pmsgTextArea.getText());
					try {
						ObjectMapper mapper = new ObjectMapper();
						String jsonString = mapper.writeValueAsString(message);
						wSocket.send(jsonString);
						pmsgTextArea.setText("");
					} catch (Exception ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				}
			}
		});
	}
	
}
