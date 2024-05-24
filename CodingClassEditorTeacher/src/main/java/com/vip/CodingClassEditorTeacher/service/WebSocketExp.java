package com.vip.CodingClassEditorTeacher.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.CodingClassEditorTeacher.App;
import com.vip.CodingClassEditorTeacher.MGMController;
import com.vip.CodingClassEditorTeacher.OGMController;
import com.vip.CodingClassEditorTeacher.model.CodeMessage;
import com.vip.CodingClassEditorTeacher.model.ConsoleMessage;
import com.vip.CodingClassEditorTeacher.model.GlobeMessage;
import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.model.Message;
import com.vip.CodingClassEditorTeacher.model.Message.MessageType;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;
import com.vip.CodingClassEditorTeacher.model.MessageDetails;
import com.vip.CodingClassEditorTeacher.model.PrivateMessage;
import com.vip.CodingClassEditorTeacher.model.ReqCodeMessage;
import com.vip.CodingClassEditorTeacher.model.Student;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;

public class WebSocketExp extends WebSocketListener{
	String msgtext="";
	VBox gbox;
	Master master;
	ObjectMapper objectMapper = new ObjectMapper();
	Student curStudent;
	JSObject javascriptConnector;
	WebSocket wSocket;
	WebEngine webEngine;
	Label receiver;
	ScrollPane pscrlPane;
	VBox pBox;
	TextArea pTextArea;
	Button pButton;
	private boolean isViewUpdating=false;
	private int count=0;
	private TeacherService teacherService=RestApiUtils.getUserService();
	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		// TODO Auto-generated method stub
		System.out.println("open");
		super.onOpen(webSocket, response);
	}
	@Override
	public void onClosed(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		System.out.println(reason);
		super.onClosed(webSocket, code, reason);
	}
	@Override
	public void onMessage(WebSocket webSocket, String text) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				//here to add ui 
				Message message = decode(text);
				if (message instanceof GlobeMessage) {
					addGlobeMessage((GlobeMessage) message);
				}else {
					if (message instanceof CodeMessage) {
						addCodeMessage((CodeMessage) message);
					}else {
						if (message instanceof ReqCodeMessage) {
							checkIsOnline((ReqCodeMessage)message);
						}else {
							if (message instanceof ConsoleMessage) {
								addConsoleMessage((ConsoleMessage) message);
							}else {
								if (message instanceof PrivateMessage) {
									addPrivateMessage((PrivateMessage) message);
								}
							}
						}
					}
				}
			}
		});
		super.onMessage(webSocket, text);
	}
	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		System.out.println(reason);
		super.onClosing(webSocket, code, reason);
	}
	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		// TODO Auto-generated method stub
		System.out.println("here socket failure");
		t.printStackTrace();
		super.onFailure(webSocket, t, response);
	}
	public void setMsgtext(String msgtext) {
		this.msgtext = msgtext;
	}
	public void setGbox(VBox gbox) {
		this.gbox = gbox;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public void setWebEngine(WebEngine webEngine) {
		this.webEngine = webEngine;
	}
	public void setpBox(VBox pBox) {
		this.pBox = pBox;
	}
	public void setpButton(Button pButton) {
		this.pButton = pButton;
		pButton.setOnAction(e->{
			if (curStudent!=null&&pTextArea!=null) {
				if (!pTextArea.getText().trim().equals("")) {
					PrivateMessage message = new PrivateMessage();
					message.setDate(new Date());
					message.setMessageType(MessageType.GLOBE);
					message.setSenderId(master.getUserId());
					message.setReceiverId(curStudent.getUserId());
					message.setMessage(pTextArea.getText());
					try {
						ObjectMapper mapper = new ObjectMapper();
						String jsonString = mapper.writeValueAsString(message);
						wSocket.send(jsonString);
						pTextArea.setText("");
					} catch (Exception ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				}
			}
		});
	}
	public void setPscrlPane(ScrollPane pscrlPane) {
		this.pscrlPane = pscrlPane;
		pscrlPane.setOnScroll(e->{
			if(e.getDeltaY()>0) {
				System.out.println("TOP");
				if (curStudent!=null) {
					if(!isViewUpdating) {
						isViewUpdating=true; 
						updateView();  
					}else {
						System.out.println("still updating");
					}
				}
			}
		});
	}
	
	public void setpTextArea(TextArea pTextArea) {
		this.pTextArea = pTextArea;
	}
	public void setReceiver(Label receiver) {
		this.receiver = receiver;
	}
	public void setCurStudent(Student curStudent) {
		this.curStudent = curStudent;
		isViewUpdating=false;
		count=0;
		updateView();
		pBox.getChildren().clear();
		javascriptConnector.call("showCode", "/*\n   loading code..... \n*/","java");
		webEngine.loadContent("<b>Loading Console.........</b>");
		receiver.setText(curStudent.getFirstName()+" "+curStudent.getLastName()
		+" ("+curStudent.getUserName()+")");
		ReqCodeMessage reqCodeMessage = new ReqCodeMessage();
		reqCodeMessage.setReciverId(curStudent.getUserId());
		reqCodeMessage.setSenderId(master.getUserId());
		reqCodeMessage.setMessageType(MessageType.REQ);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = mapper.writeValueAsString(reqCodeMessage);
			wSocket.send(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setJavascriptConnector(JSObject javascriptConnector) {
		this.javascriptConnector = javascriptConnector;
	}
	public void setwSocket(WebSocket wSocket) {
		this.wSocket = wSocket;
	}
	private void addGlobeMessage(GlobeMessage message) {
		// TODO Auto-generated method stub
		if (message.getSenderId()==master.getUserId()) {
			try {
				FXMLLoader loader = App.loadFXMLItems("mygmsg");
				AnchorPane pane = (AnchorPane)loader.load();
				MGMController controller = (MGMController) loader.getController();
				controller.setData(message);
				gbox.getChildren().add(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				FXMLLoader loader = App.loadFXMLItems("ogmsg");
				AnchorPane pane = (AnchorPane)loader.load();
				OGMController controller = (OGMController) loader.getController();
				controller.setData(message);
				gbox.getChildren().add(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void addPrivateMessage(PrivateMessage message) {
		// TODO Auto-generated method stub
		if (message.getSenderId()==master.getUserId()) {
			try {
				FXMLLoader loader = App.loadFXMLItems("mygmsg");
				AnchorPane pane = (AnchorPane)loader.load();
				MGMController controller = (MGMController) loader.getController();
				controller.setDataPM(message);
				pBox.getChildren().add(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				if(curStudent!=null) {
					if(message.getSenderId()==curStudent.getUserId()){
						FXMLLoader loader = App.loadFXMLItems("ogmsg");
						AnchorPane pane = (AnchorPane)loader.load();
						OGMController controller = (OGMController) loader.getController();
						controller.setDataPM(message);
						pBox.getChildren().add(pane);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void addCodeMessage(CodeMessage message) {
		// TODO Auto-generated method stub
		if (curStudent!=null) {
			if (curStudent.getUserId()==message.getSenderId()) {
				javascriptConnector.call("showCode", message.getCode(),message.getLanguage());
			}
		}
	}
	private void addConsoleMessage(ConsoleMessage message) {
		// TODO Auto-generated method stub
		if (curStudent!=null) {
			if (curStudent.getUserId()==message.getSenderId()) {
				webEngine.loadContent("<html>"+message.getConsole()+"</html>");
			}
		}
	}
	
	private void checkIsOnline(ReqCodeMessage message) {
		// TODO Auto-generated method stub
		if(curStudent!=null) {
			if (!message.isOnline()) {
				javascriptConnector.call("showCode", "/*\n"
			+curStudent.getFirstName()+" "+curStudent.getLastName()+" ("+curStudent.getUserName()+")"
			+" is OFFLINE \n*/","java");
				webEngine.loadContent("<b>Student Is Offline.......</b>");
			}
		}
	}
	
	private void updateView() {
		// TODO Auto-generated method stub
		MessageDetails messageDetails = new MessageDetails(master.getUserId(), curStudent.getUserId());
		Call<List<PrivateMessage>> call = teacherService.getMessages(messageDetails, count);
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
									addPrivateMessage(message);
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
	public Message decode(String s){
		// TODO Auto-generated method stub
		Message message=null;
		try {
			message = objectMapper.readValue(s, Message.class);
		} catch (JsonProcessingException e) {
			try {
				message = objectMapper.readValue(s, GlobeMessage.class);
			} catch (JsonProcessingException e2) {
				try {
					message = objectMapper.readValue(s, CodeMessage.class);
				} catch (JsonProcessingException e3) {
					try {
						message = objectMapper.readValue(s, ReqCodeMessage.class);
					} catch (JsonProcessingException e4) {
						try {
							message = objectMapper.readValue(s, ConsoleMessage.class);
						} catch (JsonProcessingException e5) {
							try {
								message = objectMapper.readValue(s, PrivateMessage.class);
							} catch (JsonProcessingException e6) {
								// TODO: handle exception
								e6.printStackTrace();
							}
						}
					}
				}
			}
			// TODO: handle exception
		}
		return message;
	}
	
	
}