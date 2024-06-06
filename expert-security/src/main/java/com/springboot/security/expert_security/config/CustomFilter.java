package com.springboot.security.expert_security.config;

import com.springboot.security.expert_security.domain.security.CustomAuthentication;
import com.springboot.security.expert_security.domain.security.IdentificacaoUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String securityHeader = request.getHeader("x-secret");

        if (securityHeader != null){
            if(securityHeader.equals("secret")) {
                var identificacaoUsuario =
                        new IdentificacaoUsuario("id-secret", "Muito Secreto",
                                "x-secret", List.of("ADMIN"));

                Authentication authentication = new CustomAuthentication(identificacaoUsuario);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
