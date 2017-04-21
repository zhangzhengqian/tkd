package com.lc.zy;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;


public class WebsocketServer {
	private static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);
	public static Map<String, Map<String, WebSocketConnection>> sockets = new HashMap<String, Map<String, WebSocketConnection>>();
	public static Map<String, String> socketMap = new HashMap<String, String>();
	
	private WebSocketHandler webSocketHandler;
	
	public static void main(String args[]){
		WebServer webServer = WebServers.createWebServer(8081).add("/websocket", new WebSocketHandler());
		webServer.start();
		logger.debug("websocket服务器启动");
	}

	public WebSocketHandler getWebSocketHandler() {
		return webSocketHandler;
	}

	public void setWebSocketHandler(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

}
