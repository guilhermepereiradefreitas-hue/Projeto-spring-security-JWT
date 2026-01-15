package Projeto.Projeto_spring_security_JWT.security;

import java.util.Date; // Importa a classe Date para armazenar a data de emissão
import java.util.List;

public class JWTObject {   // Classe para representar o objeto JWT
    private String token;  // Campo para armazenar o token JWT
    private String type = "Bearer";  // Tipo de token, padrão é "Bearer"
    @SuppressWarnings("unused") // Evita aviso de campo não utilizado
    private List<String> roles;  // Lista de papéis/roles associados ao token
    private String subject; // Campo para armazenar o assunto do token
    private Date issuedAt; // Campo para armazenar a data de emissão do token
    private Date expiration; // Campo para armazenar a data de expiração do token

    public JWTObject() {  // Construtor padrão
    }

    public JWTObject(String token) {   // Construtor com token
        this.token = token;
    }

    public String getToken() {   // Getter para o token
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}