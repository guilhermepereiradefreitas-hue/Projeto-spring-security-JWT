package Projeto.Projeto_spring_security_JWT.controller;

package Projeto.Projeto_spring_security_JWT.controller;

import Projeto.Projeto_spring_security_JWT.dto.Login;
import Projeto.Projeto_spring_security_JWT.dto.Sessao;
import Projeto.Projeto_spring_security_JWT.model.User;
import Projeto.Projeto_spring_security_JWT.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Sessao> login(@RequestBody Login loginData) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginData.getUsername(),
                    loginData.getPassword()
                )
            );

            User user = (User) authentication.getPrincipal();

            String token = jwtUtil.generateToken(user.getUsername());

            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());
            sessao.setToken(token);

            return ResponseEntity.ok(sessao);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
