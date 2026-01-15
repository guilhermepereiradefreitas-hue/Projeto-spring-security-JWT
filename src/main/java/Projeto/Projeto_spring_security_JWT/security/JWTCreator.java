package Projeto.Projeto_spring_security_JWT.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JWTCreator {  // Classe para criar e analisar tokens JWT

    public static final String HEADER_AUTHORIZATION = "Authorization";  // Nome do cabeçalho HTTP para autorização
    public static final String ROLES_AUTHORITIES = "authorities";  // Nome do campo para armazenar papéis/roles no token

    private static SecretKey getSigningKey(String key) {   // Método para obter a chave de assinatura a partir de uma string
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));    // Converte a string em bytes e cria a chave HMAC SHA
    }

    public static String create(   // Método para criar um token JWT
            String prefix, // Prefixo do token (ex: "Bearer")
            String key,  // Chave secreta para assinar o token
            JWTObject jwtObject  
    ) {

        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, jwtObject.getRoles())
                .signWith(getSigningKey(key))
                .compact();

        return prefix + " " + token;
    }

    public static JWTObject parse(
            String token,
            String prefix,
            String key
    ) {

        token = token.replace(prefix, "").trim();

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();

        JWTObject object = new JWTObject();
        object.setSubject(claims.getSubject());
        object.setIssuedAt(claims.getIssuedAt());
        object.setExpiration(claims.getExpiration());
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claims.get(ROLES_AUTHORITIES, List.class);
        object.setRoles(roles);

        return object;
    }
}
