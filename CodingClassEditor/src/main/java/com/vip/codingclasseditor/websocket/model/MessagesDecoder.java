package com.vip.codingclasseditor.websocket.model;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessagesDecoder implements Decoder.Text<Message> {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message decode(String s) throws DecodeException {
		// TODO Auto-generated method stub
		Message message=null;
		try {
			message = objectMapper.readValue(s, Message.class);
		} catch (JsonProcessingException e) {
			try {
				message = objectMapper.readValue(s, GlobeMessage.class);
			} catch (JsonProcessingException e2) {
				try {
					message = objectMapper.readValue(s, CodeMessage.class);
				} catch (JsonProcessingException e3) {
					try {
						message = objectMapper.readValue(s, ReqCodeMessage.class);
					} catch (JsonProcessingException e4) {
						try {
							message = objectMapper.readValue(s, ConsoleMessage.class);
						} catch (JsonProcessingException e5) {
							try {
								message = objectMapper.readValue(s, PrivateMessage.class);
							} catch (JsonProcessingException e6) {
								// TODO: handle exception
								e6.printStackTrace();
							}
						}
					}
				}
			}
			// TODO: handle exception
		}
		return message;
	}

	@Override
	public boolean willDecode(String s) {
		// TODO Auto-generated method stub
		return (s != null);
	}

}
