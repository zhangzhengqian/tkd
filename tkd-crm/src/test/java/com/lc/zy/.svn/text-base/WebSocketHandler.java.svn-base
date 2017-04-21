package com.lc.zy;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.WebSocketConnection;




public class WebSocketHandler extends BaseWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	
	@Override
	public void onOpen(WebSocketConnection connection) throws Exception {
		super.onOpen(connection);
		logger.debug(connection.toString());
	}

	@Override
	public void onClose(WebSocketConnection connection) throws Exception {
		super.onClose(connection);
		HttpRequest request = connection.httpRequest();
		String key = request.header("Sec-WebSocket-Key");
		if(WebsocketServer.socketMap.containsKey(key)){
			String statiumId = WebsocketServer.socketMap.get(key);
			if(WebsocketServer.sockets.containsKey(statiumId)){
				logger.debug("场馆ID{}退出",statiumId);
				Map<String,WebSocketConnection> conn = WebsocketServer.sockets.get(statiumId);
				conn.remove(key);
			}
		}
	}

	@Override
	public void onMessage(WebSocketConnection connection, String msg)
			throws Throwable {
		System.out.println(msg);
		super.onMessage(connection, msg);
		HttpRequest request = connection.httpRequest();
		Map<String,WebSocketConnection> connectionSingle = new HashMap<String, WebSocketConnection>();
		connectionSingle.put(request.header("Sec-WebSocket-Key"), connection);
		WebsocketServer.sockets.put(msg, connectionSingle);
	}

	@Override
	public void onPing(WebSocketConnection connection, byte[] msg)
			throws Throwable {
		super.onPing(connection, msg);
	}

	@Override
	public void onPong(WebSocketConnection connection, byte[] msg)
			throws Throwable {
		super.onPong(connection, msg);
	}

}
