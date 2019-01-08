package test.micky.websocket;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.WebSocketHandler;

import test.micky.websocket.socket.SockjsOption;

@Configuration
public class SockjsConfig {

    @Bean
    public WebSocketHandler echoSocketHandler() {
        return new EchoWebSocketHandler();
    }

    @Bean
    public RouterFunction<?> sockjsInfo(Optional<SockjsOption> option) {
        SockjsOption o = option.orElse(SockjsOption.builder().build());
        return RouterFunctions.route().path(o.getPrefix(),
            b -> b.GET("/info", req -> ServerResponse.ok().body(BodyInserters.fromObject(o)))
                .GET("", req -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).syncBody("Welcome to SockJs!\n")))
            .build();
    }

}
