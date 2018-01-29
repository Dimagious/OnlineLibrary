package controllers;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 23.01.2018.
 */
public class AdminDetected implements Filter {
    private static final Logger logger = Logger.getLogger(AdminDetected.class);
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = filterConfig.getInitParameter("param-name");
        if(login.equals("Admin")){
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("/login?access=noauth");
        }
    }

    @Override
    public void destroy() {

    }
}
