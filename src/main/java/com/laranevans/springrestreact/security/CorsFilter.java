package com.laranevans.springrestreact.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * I got this code from here:
 * https://spring.io/guides/tutorials/spring-security-and-angular-js/
 *
 * It was Groovy code in the example. I just modified it to be valid Java.
*/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*"); // FIXME this has to get fixed up to make secure in the future
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");
        if (!"OPTIONS".equals(request.getMethod())) {
            chain.doFilter(req, res);
        } else {
        }
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}