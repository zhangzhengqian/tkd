var ws = new WebSocket("ws://180.76.153.246:6080/websocket");
ws.onopen = function() {
	console.log("open");
	ws.send("015045c16a524e97ab69a44034c9440e");
};
ws.onmessage = function(evt) {
	alert(evt.data)
};
ws.onclose = function(evt) {
	console.log("WebSocketClosed!");
};
ws.onerror = function(evt) {
	console.log("WebSocketError!");
};