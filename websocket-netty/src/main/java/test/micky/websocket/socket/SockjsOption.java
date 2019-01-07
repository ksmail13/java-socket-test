package test.micky.websocket.socket;

import java.util.Map;
import java.util.Set;

import java.util.Collections;
import java.util.EnumSet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SockjsOption {
	private static final Set<Transport> DEFAULT_TRANSPORT = Collections.unmodifiableSet(EnumSet.allOf(Transport.class));
	
	@Builder.Default
	private final String prefix = "";
	private final boolean websocket;
	private final Set<Transport> transports = DEFAULT_TRANSPORT;
	private final long responseLimit = 128 * 1024;
	private final Map<String, Object> fayeServerOptions;
	@Builder.Default
	private final long heartbeatDelay = 25000L;
	@Builder.Default
	private final long disconnectDelay = 5000L;
	@Builder.Default
	private final String sockjsUrl = "https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js";
	
}
