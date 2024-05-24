package com.vip.codingclasseditor.projection;

public class MessageDetails {
	long senderId;
	long receiverId;
	public MessageDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MessageDetails(long senderId, long receiverId) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	
	
}
