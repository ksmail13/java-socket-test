package test.micky.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ws/echo")
@Slf4j
public class SockjsController {

    @GetMapping("/info")
    @ResponseBody
    public Map<String, Object> info(@RequestParam(name = "t") long time) {
        Map<String, Object> info = new HashMap<>();
        info.put("websocket", true);
        info.put("transports", Arrays.asList("websocket", "websocket-raw"));
        info.put("cookie_needed", false);
        info.put("entropy", new Random().nextInt());
        info.put("base_url", "/ws/echo");

        log.info("present info {}", new Date(time));
        return info;
    }
}
