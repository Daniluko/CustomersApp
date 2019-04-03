package springboot.filter;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Danylo on 02.04.2019
 */
@Component
public class ThreadContextFilter implements Filter {
    private static final String UUID_FIELD = "uuid";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            ThreadContext.put(UUID_FIELD, UUID.randomUUID().toString());
            chain.doFilter(request, response);
        } finally {
            ThreadContext.remove(UUID_FIELD);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
