package test.micky.websocket;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableScheduling
@Slf4j
public class Config {

    @Configuration
    @EnableWebSocket
    public static class WebSocketConf implements WebSocketConfigurer {

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(echoSocketHandler(), "/ws/echo/").withSockJS()
                    .setClientLibraryUrl("/js/sockjs.js")
                    .setHeartbeatTime(5000);
        }

        @Bean
        public WebSocketHandler echoSocketHandler() {
            return new EchoWebSocketHandler();
        }

    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.failOnUnknownProperties(false);
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }

}
