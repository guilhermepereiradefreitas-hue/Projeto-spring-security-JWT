package Projeto.Projeto_spring_security_JWT.controller;

import Projeto.Projeto_spring_security_JWT.model.User;
import Projeto.Projeto_spring_security_JWT.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;     
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController  // Indica que esta classe é um controlador REST
@RequestMapping("/users")  // Mapeia as requisições para /users
public class UserController {  // Controlador para gerenciar operações relacionadas a usuários
    private final UserService userService;  // Injeção de dependência do serviço de usuários

    public UserController(UserService userService) {  // Construtor para injeção de dependência
        this.userService = userService;  // Inicializa o serviço de usuários
    }

    @PostMapping  // Mapeia requisições POST para este método
    public ResponseEntity<User> createUser(@RequestBody User user) {  // Método para criar um novo usuário
        User createdUser = userService.createUser(user);  // Chama o serviço para criar o usuário
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);  // Retorna a resposta com status 201 (Created)
    }
}
