package test.micky.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

@Controller
public class TestController {

    @RequestMapping("/")
    public Callable<ModelAndView> index() {
        return getModelAndViewCallable("index");
    }

    @RequestMapping("/echo")
    public Callable<ModelAndView> echoTestPage() {
        return getModelAndViewCallable("echo");
    }

    @RequestMapping("/rand")
    public Callable<ModelAndView> randTestPage() {
        return getModelAndViewCallable("rand");
    }

    private Callable<ModelAndView> getModelAndViewCallable(String rand) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(rand);

        return () -> mav;
    }
}
