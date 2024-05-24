package com.vip.codingclasseditor.websocket.model;

import java.util.Date;

public class ReqCodeMessage extends Message{
	long reciverId;
	boolean isOnline;

	public ReqCodeMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReqCodeMessage(long msgId, long senderId, Date date, MessageType messageType) {
		super(msgId, senderId, date, messageType);
		// TODO Auto-generated constructor stub
	}

	public ReqCodeMessage(long msgId, long senderId, long reciverId, MessageType messageType,boolean isOnline) {
		super();
		this.reciverId = reciverId;
		this.isOnline = isOnline;
	}
	
	public void setReciverId(long reciverId) {
		this.reciverId = reciverId;
	}
	public long getReciverId() {
		return reciverId;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}
