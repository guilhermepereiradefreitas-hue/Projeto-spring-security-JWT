package Projeto.Projeto_spring_security_JWT.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/login",    // Endpoint de login
            "/h2-console/**",  // Endpoint do console H2
            "/swagger-resources",  // Endpoint de recursos do Swagger
            "/swagger-resources/**", // Endpoint de recursos do Swagger
            "configuration/ui",  // Endpoint de configuração da interface do Swagger
            "/configuration/security",  // Endpoint de configuração de segurança do Swagger
            "/swagger-ui.html",  // Endpoint da interface do Swagger
            "/webjars/**" // Endpoint de webjars
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTFilter jwtFilter) throws Exception {

         http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .headers(headers ->
                headers.frameOptions(frame -> frame.disable())
            )

            // Equivalente a cors().and().csrf().disable()
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())

            // JWT Filter (ANTES do UsernamePasswordAuthenticationFilter)
            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            )

            // Autorização
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users")
                    .hasAnyRole("USERS", "MANAGERS")
                .requestMatchers("/managers")
                    .hasRole("MANAGERS")
                .anyRequest().authenticated()
                .and().httpSecurity()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS

            )

            // Stateless (JWT)
            
            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class        
            );

        return http.build();
    }

}
