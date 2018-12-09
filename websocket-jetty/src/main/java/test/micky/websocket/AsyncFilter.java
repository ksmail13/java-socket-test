package test.micky.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class AsyncFilter implements Filter {

    private Executor executor;

    @Autowired
    public AsyncFilter(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("init async filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("try async request {}", request.getRemotePort() );

        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.setTimeout(1000L);

        executor.execute(() -> {
            try {
                chain.doFilter(asyncContext.getRequest(), asyncContext.getResponse());
            } catch (IOException | ServletException e) {
                log.error("unknown exception", e);
            } finally {
                asyncContext.dispatch();
            }
        });
    }

    @Override
    public void destroy() {
        log.debug("destroy async filter");
    }
}
