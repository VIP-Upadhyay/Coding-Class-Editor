package com.vip.codingclasseditor.websocket.server;


import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.vip.codingclasseditor.service.MessageHibernateService;
import com.vip.codingclasseditor.websocket.config.SpringWebSocketContextConfig;
import com.vip.codingclasseditor.websocket.model.CodeMessage;
import com.vip.codingclasseditor.websocket.model.CodeMessageEncoder;
import com.vip.codingclasseditor.websocket.model.ConsoleMessage;
import com.vip.codingclasseditor.websocket.model.ConsoleMessageEncoder;
import com.vip.codingclasseditor.websocket.model.GlobeMessage;
import com.vip.codingclasseditor.websocket.model.GlobeMessageEncoder;
import com.vip.codingclasseditor.websocket.model.Message;
import com.vip.codingclasseditor.websocket.model.MessagesDecoder;
import com.vip.codingclasseditor.websocket.model.PrivateMessage;
import com.vip.codingclasseditor.websocket.model.PrivateMessageEncoder;
import com.vip.codingclasseditor.websocket.model.ReqCodeMessage;
import com.vip.codingclasseditor.websocket.model.ReqCodeMessageEncoder;


@Component
@ServerEndpoint(value = "/msg/{userId}",
decoders = MessagesDecoder.class,
encoders = {GlobeMessageEncoder.class,CodeMessageEncoder.class,ReqCodeMessageEncoder.class,
		ConsoleMessageEncoder.class,PrivateMessageEncoder.class})
public class WebSocketServer { 
	
	MessageHibernateService messageHibernateService = SpringWebSocketContextConfig.getBean(MessageHibernateService.class);
	
	@OnOpen
	public void myOnOpen (@PathParam("userId") String userId,Session session) {
	   System.out.println ("WebSocket opened: "+userId);
	   session.getUserProperties().put("userId", userId);
	}
	
	@OnMessage
	public void onMessage(Session session,Message message) {
		if (message instanceof GlobeMessage) {
			GlobeMessage globeMessage =(GlobeMessage) message;
			sendGMessage(session, globeMessage);
		}else {
			if (message instanceof CodeMessage) {
				CodeMessage codeMessage = (CodeMessage)message;
				sendCodeMessage(session,codeMessage,codeMessage.getReciverId());
			}else {
				if (message instanceof ReqCodeMessage) {
					ReqCodeMessage reqCodeMessage = (ReqCodeMessage) message;
					reqCodeMessage.setOnline(false);
					sendReqCodeMessage(session,reqCodeMessage,reqCodeMessage.getReciverId());
				}else {
					if (message instanceof ConsoleMessage) {
						ConsoleMessage consoleMessage = (ConsoleMessage) message;
						sendCodeMessage(session,consoleMessage,consoleMessage.getReciverId());
					}else {
						if (message instanceof PrivateMessage) {
							PrivateMessage privateMessage = (PrivateMessage) message;
							messageHibernateService.saveMessage(privateMessage);
							sendCodeMessage(session, privateMessage, privateMessage.getReceiverId());
							session.getAsyncRemote().sendObject(message);
						}
					}
				}
			}
		}
	}
	private void sendReqCodeMessage(Session session, Message message,long reciverId) {
		try {
			boolean isONLINE=false;
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					Long userid = Long.parseLong((String) s.getUserProperties().get("userId"));
					if (userid==reciverId) {
						isONLINE=true;
						s.getAsyncRemote().sendObject(message);
					}
				}
			}
			if (!isONLINE) {
				session.getAsyncRemote().sendObject(message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void sendCodeMessage(Session session, Message message,long reciverId) {
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					Long userid = Long.parseLong((String) s.getUserProperties().get("userId"));
					if (userid==reciverId) {
						s.getAsyncRemote().sendObject(message);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void sendGMessage(Session session,Message message) {
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					s.getAsyncRemote().sendObject(message);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void myOnClose (CloseReason reason) {
	   System.out.println("Closing a WebSocket due to "+reason.getReasonPhrase());
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("Error Occured");
		error.printStackTrace();
	}
}
