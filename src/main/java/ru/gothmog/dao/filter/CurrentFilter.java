package ru.gothmog.dao.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CurrentFilter implements Filter {

    private ServletContext context;

    private static final Logger log= Logger.getLogger(CurrentFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("Filter initialized");
        log.info("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encoding = request.getCharacterEncoding();

        if (!"UTF-8".equalsIgnoreCase(encoding)) {
            response.setCharacterEncoding("UTF-8");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("Filter destroyed");
    }
}
