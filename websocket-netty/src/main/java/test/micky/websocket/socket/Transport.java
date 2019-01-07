package test.micky.websocket.socket;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Transport {
	EVENTSOURCE("eventsource"),
	HTMLFILE("htmlfile"),
	JSONP_POLLING("json-polling"),
	WEBSOCKET("websocket"),
	WEBSOCKET_RAW("websocket-raw"),
	XHR_POLLING("xhr-polling"),
	XHR_STREAMING("xhr-streaming");
	
	private final String json;
	
	Transport(String json) {
		this.json = json;
	}
	
	@JsonValue
	public String toJson() {
		return json;
	}
	
}
