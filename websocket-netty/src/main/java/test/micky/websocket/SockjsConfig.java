package test.micky.websocket;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class SockjsConfig {

    @Bean
    public HandlerMapping handlerMapping(WebSocketHandler socketHandler) {
        Map<String, WebSocketHandler> map = Collections.singletonMap("/ws/echo", socketHandler);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        mapping.setOrder(-1);

        return mapping;
    }

    @Bean
    public RouterFunction<ServerResponse> ajaxEcho(EchoWebSocketHandler socketHandler) {
        return RouterFunctions.route()
                .GET("/ajax/echo", socketHandler::echoByAjax)
                .build();
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
