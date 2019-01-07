package test.micky.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Slf4j
public class EchoWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        log.info("socket connected {}({})", session.getId(), session.getAttributes());
        return session.receive()
                .map(Mono::just)
                .doOnNext(session::send)
                .doFinally(type -> log.info("{} is {}", session.getId(), type))
                .then();
    }
}
