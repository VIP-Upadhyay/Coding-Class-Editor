package com.vip.codingclasseditor.websocket.model;

import java.util.Date;



public class GlobeMessage extends Message {
	String message;
	String username;
	String name;
	public GlobeMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GlobeMessage(long msgId, long senderId, Date date, MessageType messageType) {
		super(msgId, senderId, date, messageType);
		// TODO Auto-generated constructor stub
	}
	public GlobeMessage(long msgId, long senderId, Date date, MessageType messageType, String message,String username,String name) {
		super();
		setMsgId(msgId);
		setSenderId(senderId);
		setDate(date);
		setMessageType(messageType);
		this.message = message;
		this.username = username;
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
