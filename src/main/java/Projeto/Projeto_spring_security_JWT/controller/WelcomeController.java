package Projeto.Projeto_spring_security_JWT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    // 🔓 Rota pública (não precisa de token)
    @GetMapping("/public")
    public String publicRoute() {
        return "Rota pública funcionando!";
    }

    // 🔐 Rota protegida (precisa de JWT)
    @GetMapping("/welcome")
    public String welcome() {
        return "Bem-vindo! JWT válido 🎉";
    }

    // 🔐🔑 Rota protegida por ROLE
    @GetMapping("/admin")
    public String admin() {
        return "Acesso permitido apenas para ADMIN";
    }
}

