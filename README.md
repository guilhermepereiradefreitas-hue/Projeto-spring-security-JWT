🔐 Adicionando Segurança a uma API REST com Spring Security e Integração com React

Este projeto demonstra como adicionar autenticação e autorização a uma API REST utilizando Spring Boot 3, Spring Security 6 e JWT (JSON Web Token), além da integração completa com um frontend em React JS.

O objetivo é apresentar uma arquitetura moderna, segura e escalável, baseada em API Stateless + SPA (Single Page Application).

🚀 Tecnologias Utilizadas
Backend

☕ Java 17

🌱 Spring Boot 3

🔐 Spring Security 6

🔑 JWT (JSON Web Token)

🗄 H2 Database (desenvolvimento)

📦 Maven

Frontend

⚛️ React JS

🌐 Axios

🔀 React Router DOM

🧠 Conceitos Aplicados

Autenticação Stateless com JWT

Autorização baseada em Roles

SecurityFilterChain (padrão moderno)

Filtro JWT customizado (JWTFilter)

Login via AuthenticationManager

Proteção de rotas no React

Envio automático de token no Header Authorization

Separação clara entre Backend e Frontend

📂 Estrutura do Projeto
Backend (Spring Boot)
src/main/java
└── Projeto/Projeto_spring_security_JWT
    ├── controller
    │   ├── LoginController.java
    │   └── WelcomeController.java
    ├── security
    │   ├── WebSecurityConfig.java
    │   ├── JWTFilter.java
    │   └── JWTUtil.java
    ├── model
    │   └── User.java
    ├── repository
    │   └── UserRepository.java
    └── dto
        ├── Login.java
        └── Sessao.java

🔑 Autenticação com JWT
📌 Fluxo de Autenticação
React (Login)
   ↓ POST /login
Spring Boot
   ↓ valida usuário e senha
   ↓ gera JWT
React
   ↓ salva token (localStorage)
   ↓ envia token no Authorization Header
Spring Boot
   ↓ valida JWT (JWTFilter)
   ↓ libera acesso às rotas protegidas

🔓 Endpoint de Login
POST /login

Request Body:

{
  "username": "admin",
  "password": "123"
}


Response:

{
  "login": "admin",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

🔐 Endpoints da API
Método	Endpoint	Acesso
GET	/public	Público
POST	/login	Público
GET	/welcome	JWT válido
GET	/admin	ROLE_ADMIN
Exemplo de Header JWT
Authorization: Bearer SEU_TOKEN_AQUI

⚙️ Configuração de Segurança

A segurança da aplicação é configurada utilizando SecurityFilterChain, substituindo o antigo WebSecurityConfigurerAdapter.

Principais configurações:

CSRF desabilitado (API REST)

Sessão Stateless

Liberação do /login e /h2-console

Validação do JWT antes do UsernamePasswordAuthenticationFilter

🗄 H2 Console (Desenvolvimento)

O banco H2 é utilizado apenas para desenvolvimento.

Acesso:
http://localhost:8080/h2-console


Configuração padrão:

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (vazio)

⚛️ Integração com React JS
📦 Configuração do Axios
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;

🔐 Login no React
api.post("/login", {
  username: "admin",
  password: "123"
}).then(response => {
  localStorage.setItem("token", response.data.token);
});

🔒 Consumindo Endpoint Protegido
api.get("/welcome")
  .then(response => console.log(response.data))
  .catch(() => console.error("Acesso negado"));

🧭 Proteção de Rotas no React
function PrivateRoute({ children }) {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/" />;
}

🧪 Testes

Postman para testar /login e rotas protegidas

Navegador para testar React

H2 Console para inspeção do banco

📌 Boas Práticas Utilizadas

API Stateless

JWT no Header Authorization

Separação por camadas

DTOs para entrada e saída

Roles com prefixo ROLE_

Código compatível com Java 17

🚀 Próximos Passos

♻️ Refresh Token

🔐 Autorização com @PreAuthorize

🎨 UI com Material UI ou Tailwind

🐳 Docker (Backend + Frontend)

🗄 Migração H2 → PostgreSQL

🌐 Deploy em produção

👨‍💻 Autor

Projeto desenvolvido para aprendizado prático de segurança em APIs REST com Spring Security + JWT e integração com React JS.

