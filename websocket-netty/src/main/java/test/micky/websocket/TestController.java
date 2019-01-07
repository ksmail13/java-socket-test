package test.micky.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/echo")
    public String echoTestPage() {
        return "echo";
    }

    @RequestMapping("/rand")
    public String randTestPage() {
        return "rand";
    }
}
