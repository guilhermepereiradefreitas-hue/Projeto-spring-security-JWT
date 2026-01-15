package Projeto.Projeto_spring_security_JWT.service;

import Projeto.Projeto_spring_security_JWT.repository.UserRepository;
import Projeto.Projeto_spring_security_JWT.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service   // Indica que esta classe é um componente de serviço gerenciado pelo Spring IoC container 
public class UserService { // Serviço para gerenciar operações relacionadas a usuários

    private final UserRepository repository;  // Injeção de dependência do repositório de usuários
    private final PasswordEncoder encoder;  // Injeção de dependência do codificador de senhas

    public UserService(UserRepository repository,  // Injeção de dependência do repositório e do codificador de senhas
                       PasswordEncoder encoder) {
        this.repository = repository;  // Inicializa o repositório
        this.encoder = encoder;  // Inicializa o codificador de senhas
    }

    public User createUser(User user) {  // Método para criar um novo usuário 

        if (repository.existsByUsername(user.getUsername())) {  // Verifica se o nome de usuário já existe
            throw new IllegalArgumentException("Username already exists");  // Lança uma exceção se o nome de usuário já estiver em uso
        }

        // Criptografa a senha antes de salvar
        user.setPassword(encoder.encode(user.getPassword()));

        return repository.save(user);
    }
} 