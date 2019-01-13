package test.micky.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class EchoWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        log.info("socket connected {}({})", session.getId(), session.getAttributes());
        return session.send(session.receive().map(msg -> String.format("%s / %s", session.getId(), msg.getPayloadAsText()))
                .doOnNext(msg -> log.debug("recv msg {}", msg))
                .map(session::textMessage))
                .doOnError(t -> log.error("error by {}", t))
                .doFinally(type -> log.info("finally {} is {}", session.getId(), type));
    }

    public Mono<ServerResponse> echoByAjax(ServerRequest req) {
        Mono<String> body = req.bodyToMono(String.class);
        Optional<MediaType> mediaType = req.headers().contentType();
        return ServerResponse.ok().contentType(mediaType.orElse(MediaType.TEXT_PLAIN))
                .body(BodyInserters.fromObject(body));
    }
}
