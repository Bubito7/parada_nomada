package com.paradanomada.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Se asegura de capturar el origen correctamente
        String originHeader = request.getHeader("Origin");

        // Configura las cabeceras CORS de manera correcta
        response.setHeader("Access-Control-Allow-Origin", originHeader);  // Corregido aquí
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");  // Corregido
        response.setHeader("Access-Control-Max-Age", "3600");  // Se ajustó para 1 hora
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");  // Ajustado según la seguridad

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {}

}
