package com.vip.CodingClassEditorTeacher.model;

import java.util.Date;


public class CodeMessage extends Message{
	long reciverId;
	String code;
	String language;
	
	public CodeMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CodeMessage(long msgId, long senderId, Date date, MessageType messageType) {
		super(msgId, senderId, date, messageType);
		// TODO Auto-generated constructor stub
	}
	public CodeMessage(long msgId, long senderId, long reciverId, String code, MessageType messageType,String language) {
		super();
		setMsgId(msgId);
		setSenderId(senderId);
		setMessageType(messageType);
		this.reciverId = reciverId;
		this.code = code;
		this.language = language;
	}
	public long getReciverId() {
		return reciverId;
	}
	public void setReciverId(long reciverId) {
		this.reciverId = reciverId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
