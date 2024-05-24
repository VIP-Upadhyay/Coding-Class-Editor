package com.vip.codingclasseditor.websocket.model;

import java.util.Date;

public class PrivateMessage extends Message{
	long receiverId;
	String message;
	public PrivateMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PrivateMessage(long msgId, long senderId, Date date, MessageType messageType) {
		super(msgId, senderId, date, messageType);
		// TODO Auto-generated constructor stub
	}
	public PrivateMessage(long msgId, long senderId, long receiverId, String message, Date date) {
		super();
		setMsgId(msgId);
		setSenderId(senderId);
		setDate(date);
		setMessageType(MessageType.PRIVATE);
		this.receiverId = receiverId;
		this.message = message;
	}
	public PrivateMessage(long msgId, long senderId, long receiverId, String message, Date date, MessageType messageType) {
		super();
		setMsgId(msgId);
		setSenderId(senderId);
		setDate(date);
		setMessageType(messageType);
		this.receiverId = receiverId;
		this.message = message;
	}
	
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public com.vip.codingclasseditor.model.PrivateMessage convertToPrivateMessage(){
		com.vip.codingclasseditor.model.PrivateMessage privateMessage = new com.vip.codingclasseditor.model.PrivateMessage();
		privateMessage.setSenderId(senderId);
		privateMessage.setReceiverId(receiverId);
		privateMessage.setDate(date);
		privateMessage.setMessage(message);
		return privateMessage;
	}
	
	
}
