package com.vip.CodingClassEditorTeacher.model;

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
	
	
	
}
