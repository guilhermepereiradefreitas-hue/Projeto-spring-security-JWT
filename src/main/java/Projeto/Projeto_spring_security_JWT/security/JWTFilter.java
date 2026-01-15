package Projeto.Projeto_spring_security_JWT.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {   // Filtro que será executado uma vez por requisição
  
    private final String prefix;    // Prefixo do token JWT (ex: "Bearer ")
    private final String key;    // Chave secreta para validar o token JWT

    public JWTFilter(String prefix, String key) {  // Construtor para inicializar o prefixo e a chave
        this.prefix = prefix; // Inicializa o prefixo
        this.key = key;     // Inicializa a chave
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);

        if (header != null && header.startsWith(prefix)) {
            try {
                var jwtObject = JWTCreator.parse(header, prefix, key);

                var authorities = jwtObject.getRoles()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                var authentication =
                        new UsernamePasswordAuthenticationToken(
                                jwtObject.getSubject(),
                                null,
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
