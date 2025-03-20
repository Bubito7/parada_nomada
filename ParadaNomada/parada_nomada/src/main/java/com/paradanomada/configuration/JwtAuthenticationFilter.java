package com.paradanomada.configuration;

import com.paradanomada.services.jwt.UserService;
import com.paradanomada.utils.JWTUtil;
import io.jsonwebtoken.Jwt;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;  // Utilidad para gestionar la autenticación con JWT
    private final UserService userService;  // Servicio para cargar los detalles del usuario desde la base de datos

    // Constructor con inyección de dependencias
    @Autowired
    public JwtAuthenticationFilter(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;  // Asignar la utilidad JWT
        this.userService = userService;  // Asignar el servicio del usuario
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");  // Obtiene el encabezado 'Authorization' de la solicitud
        final String jwt;  // Declaración de variable para almacenar el JWT
        final String userEmail;  // Declaración de variable para almacenar el correo del usuario extraído del JWT

        // Verifica si el encabezado 'Authorization' está presente y si tiene el prefijo 'Bearer '
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);  // Si no es un encabezado válido, pasa al siguiente filtro
            return;
        }

        jwt = authHeader.substring(7);  // Extrae el token JWT del encabezado (eliminando el prefijo 'Bearer ')
        userEmail = jwtUtil.extractUserName(jwt);  // Extrae el correo del usuario desde el token JWT

        // Verifica si el correo extraído no está vacío y si no hay ninguna autenticación en el contexto de seguridad
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carga los detalles del usuario (nombre de usuario y roles) desde la base de datos
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            // Si el token es válido, establece el contexto de seguridad
            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();  // Crea un contexto de seguridad vacío
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());  // Crea un token de autenticación con los detalles del usuario

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  // Añade detalles adicionales del request al token
                context.setAuthentication(authToken);  // Asocia el token de autenticación con el contexto de seguridad
                SecurityContextHolder.setContext(context);  // Establece el contexto de seguridad
            }
        }

        filterChain.doFilter(request, response);  // Continúa con el siguiente filtro en la cadena de filtros
    }
}
