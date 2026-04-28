package com.harini.account_service.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getRequestURI();

        if (path.startsWith("/account")) {

            String auth = req.getHeader("Authorization");

            if (auth == null || !auth.startsWith("Bearer ")) {
                ((HttpServletResponse) response)
                        .sendError(401, "Token Missing");
                return;
            }

            String token = auth.substring(7);

            try {
                jwtUtil.extractEmail(token);
            } catch (Exception e) {
                ((HttpServletResponse) response)
                        .sendError(401, "Invalid Token");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}