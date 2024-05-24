package com.vip.CodingClassEditorStudent.model;

import java.util.Date;


public class ConsoleMessage extends Message{
	long reciverId;
	String console;
	public ConsoleMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsoleMessage(long msgId, long senderId, Date date, MessageType messageType) {
		super(msgId, senderId, date, messageType);
		// TODO Auto-generated constructor stub
	}
	public ConsoleMessage(long msgId, long senderId, long reciverId, String console, MessageType messageType) {
		super();
		setMsgId(msgId);
		setSenderId(senderId);
		setMessageType(messageType);
		this.reciverId = reciverId;
		this.console = console;
	}
	public long getReciverId() {
		return reciverId;
	}
	public void setReciverId(long reciverId) {
		this.reciverId = reciverId;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	
}
