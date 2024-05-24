package com.vip.CodingClassEditorStudent.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodedu.terminalfx.TerminalTab;
import com.vip.CodingClassEditorStudent.App;
import com.vip.CodingClassEditorStudent.MGMController;
import com.vip.CodingClassEditorStudent.OGMController;
import com.vip.CodingClassEditorStudent.model.CodeMessage;
import com.vip.CodingClassEditorStudent.model.ConsoleMessage;
import com.vip.CodingClassEditorStudent.model.GlobeMessage;
import com.vip.CodingClassEditorStudent.model.Master;
import com.vip.CodingClassEditorStudent.model.Message;
import com.vip.CodingClassEditorStudent.model.ReqCodeMessage;
import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.model.Message.MessageType;
import com.vip.CodingClassEditorStudent.model.PrivateMessage;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketExp extends WebSocketListener{
	String msgtext="";
	VBox gbox,pmsgvbox;
	Student student;
	ObjectMapper objectMapper = new ObjectMapper();
	JSObject javascriptConnector;
	TerminalTab terminalTab;
	WebSocket wSocket;
	Master master;
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
					if (message instanceof ReqCodeMessage) {
						sendCode();
					}else {
						if (message instanceof PrivateMessage) {
							addPrivateMessage((PrivateMessage) message);
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
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setJavascriptConnector(JSObject javascriptConnector) {
		this.javascriptConnector = javascriptConnector;
	}
	public void setTerminalTab(TerminalTab terminalTab) {
		this.terminalTab = terminalTab;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public void setwSocket(WebSocket wSocket) {
		this.wSocket = wSocket;
	}
	public void setPmsgvbox(VBox pmsgvbox) {
		this.pmsgvbox = pmsgvbox;
	}
	private void addGlobeMessage(GlobeMessage message) {
		// TODO Auto-generated method stub
		if (message.getSenderId()==student.getUserId()) {
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
	public void addPrivateMessage(PrivateMessage message) {
		// TODO Auto-generated method stub
		if (message.getSenderId()==student.getUserId()) {
			try {
				FXMLLoader loader = App.loadFXMLItems("mygmsg");
				AnchorPane pane = (AnchorPane)loader.load();
				MGMController controller = (MGMController) loader.getController();
				controller.setDataPM(message);
				pmsgvbox.getChildren().add(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				FXMLLoader loader = App.loadFXMLItems("ogmsg");
				AnchorPane pane = (AnchorPane)loader.load();
				OGMController controller = (OGMController) loader.getController();
				controller.setDataPM(message);
				pmsgvbox.getChildren().add(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	private void sendCode() {
		javascriptConnector.call("sendCode");
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
	}
}
