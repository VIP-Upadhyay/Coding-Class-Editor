package com.vip.codingclasseditor.websocket.model;

import java.util.Date;

public class Message {
	long msgId;
	long senderId;
	Date date;
	MessageType messageType;
	public enum MessageType {
        GLOBE,
        PRIVATE,
        CODE,
        CONSOLE,
        REQ
    }
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(long msgId, long senderId, Date date, MessageType messageType) {
		super();
		this.msgId = msgId;
		this.senderId = senderId;
		this.date = date;
		this.messageType = messageType;
	}
	
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
}
