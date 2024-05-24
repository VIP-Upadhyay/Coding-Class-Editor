package com.vip.CodingClassEditorTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.CodingClassEditorTeacher.model.GlobeMessage;
import com.vip.CodingClassEditorTeacher.model.Master;
import com.vip.CodingClassEditorTeacher.model.Message.MessageType;
import com.vip.CodingClassEditorTeacher.service.RetrofitClient;
import com.vip.CodingClassEditorTeacher.service.WebSocketExp;
import com.vip.CodingClassEditorTeacher.utils.MasterHolder;
import com.vip.CodingClassEditorTeacher.utils.RestApiUtils;
import com.vip.CodingClassEditorTeacher.utils.WebSocketHolder;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import okhttp3.Request;
import okhttp3.WebSocket;

public class HomeController implements Initializable {

	@FXML
	Button logoutbtn,profilebtn,homebtn,msgbtn,faqbtn,abtbtn,msgclose,msgSend;
	
	@FXML
	TextArea msgTextArea;  
	
	@FXML
	ScrollPane msgScrlPane;
	
	@FXML
	VBox msgvbox;
	
	@FXML
	Pane msgPane;
	
	@FXML
	AnchorPane maincontainer; 

	WebSocket wSocket;
	Master master;
	
	private boolean isMsgBoxUP = true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		MasterHolder masterHolder = MasterHolder.getInstanceHolder();
		master = masterHolder.getMaster();
		System.out.println(master.getUserName());
	    profilebtn.setGraphic(App.loadGraphics("img/userico.png",25));
	    homebtn.setGraphic(App.loadGraphics("img/homeico.png",25));
	    msgbtn.setGraphic(App.loadGraphics("img/msgico.png",25));
	    abtbtn.setGraphic(App.loadGraphics("img/abtico.png", 25));
	    msgclose.setGraphic(App.loadGraphics("img/close.png", 25));
	    msgSend.setGraphic(App.loadGraphics("img/send.png", 25));
	    AnchorPane pane = App.setScreen("homescreen");
	    maincontainer.getChildren().add(pane);
	    AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0); 
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
	    Request request = new Request.Builder().url("ws://"+RestApiUtils.IP_ADDRESS+"/msg/"+master.getUserId()).build();
		WebSocketExp exp = new WebSocketExp();
		exp.setGbox(msgvbox);
		exp.setMaster(master);
		msgvbox.heightProperty().addListener(obs->{
			msgScrlPane.setVvalue(1D);
		});
		wSocket = RetrofitClient.getClient().newWebSocket(request, exp);
	    WebSocketHolder webSocketHolder = WebSocketHolder.getInstanceHolder();
	    webSocketHolder.setwSocket(wSocket);
	    webSocketHolder.setWebSocketExp(exp);
	}
	
	
	
	
	@FXML
	public void onLogout() {
		System.out.println("l");
		try {
			wSocket.cancel();
			App.setRootWithCss("loginsignup","application");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	@FXML
	public void onProfile() {
		System.out.println("p");
		maincontainer.getChildren().remove(0);
		AnchorPane pane = App.setScreen("profilescreen");
		maincontainer.getChildren().add(pane);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0); 
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
	}
	
	@FXML
	public void onHome() {
		System.out.println("h");
		maincontainer.getChildren().remove(0);
		AnchorPane pane = App.setScreen("homescreen");
		maincontainer.getChildren().add(pane);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0); 
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
	}
	@FXML
	public void onMsg() {
		if (isMsgBoxUP) {
			translateDown();
		}else {
			translateUP();
		}
	}
	@FXML
	public void onFAQ() {
		System.out.println("faq");
		maincontainer.getChildren().remove(0);
		AnchorPane pane = App.setScreen("faqscreen");
		maincontainer.getChildren().add(pane);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0); 
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
	}
	@FXML
	public void onAbt() { 
		System.out.println("abt");
		maincontainer.getChildren().remove(0);
		AnchorPane pane = App.setScreen("aboutscreen");
		maincontainer.getChildren().add(pane);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0); 
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
	}
	public void translateDown() {
		TranslateTransition transition = new TranslateTransition(Duration.millis(700), msgPane);
		transition.setByY(480);
		transition.play();
		isMsgBoxUP=false;
	}
	public void translateUP() {
		TranslateTransition transition = new TranslateTransition(Duration.millis(700), msgPane);
		transition.setByY(-480);
		transition.play();
		isMsgBoxUP=true;
	}
	
	@FXML
	public void onMsgSend() {
		if (!msgTextArea.getText().trim().equals("")) {
			GlobeMessage message = new GlobeMessage();
			message.setDate(new Date());
			message.setMessageType(MessageType.GLOBE);
			message.setSenderId(master.getUserId());
			message.setMessage(msgTextArea.getText());
			message.setName(master.getFirstName()+" "+master.getLastName());
			message.setUsername(master.getUserName());
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(message);
				wSocket.send(jsonString);
				msgTextArea.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@FXML
	public void onMsgCLose() {
		if (!isMsgBoxUP) {
			translateUP();
		}
	}
	
}
