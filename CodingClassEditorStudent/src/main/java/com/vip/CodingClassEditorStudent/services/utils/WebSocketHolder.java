package com.vip.CodingClassEditorStudent.services.utils;


import com.vip.CodingClassEditorStudent.services.WebSocketExp;

import okhttp3.WebSocket;

public class WebSocketHolder {

    private WebSocket wSocket;
    private WebSocketExp webSocketExp;
    private final static WebSocketHolder INSTANCE_HOLDER = new WebSocketHolder();
	public static WebSocketHolder getInstanceHolder() {
		return INSTANCE_HOLDER;
	}
	public WebSocket getwSocket() {
		return wSocket;
	}
	public void setwSocket(WebSocket wSocket) {
		this.wSocket = wSocket;
	}
	public WebSocketExp getWebSocketExp() {
		return webSocketExp;
	}
	public void setWebSocketExp(WebSocketExp webSocketExp) {
		this.webSocketExp = webSocketExp;
	}
}
