package com.staff.location.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XContentTypeOptionsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) response;
        httpResp.setHeader("X-Content-Type-Options", "nosniff");

        chain.doFilter(request, response);
    }
}