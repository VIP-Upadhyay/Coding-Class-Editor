package com.vip.codingclasseditor.websocket.model;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReqCodeMessageEncoder implements Encoder.Text<ReqCodeMessage> {
	ObjectMapper mapper = new ObjectMapper();
	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ReqCodeMessage object) throws EncodeException {
		// TODO Auto-generated method stub
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
