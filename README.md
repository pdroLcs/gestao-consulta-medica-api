# API de Gestão de Consultas Médicas

API REST desenvolvida para gerenciamento de consultas médicas, permitindo cadastro de pacientes, médicos e agendamento de consultas.

---

## Tecnologias utilizadas
- Java
- Spring Boot
- PostgreSQL
- JPA / Hibernate
- Maven

---

## Funcionalidades
- Cadastro de pacientes
- Cadastro de médicos
- Agendamento de consultas
- Cancelamento de consultas
- Listagem de consultas por paciente e médico

---

## Como executar o projeto

### Pré-requisitos
- Java 21+
- Maven
- PostgreSQL

### Passos

Clonar o repositório
```cmd
git clone https://github.com/pdroLcs/gestao-consulta-medica-api.git
```

Entrar na pasta
```cmd
cd gestao-consulta-medica-api
```

Rodar a aplicação
```cmd
./mvnw spring-boot:run
```

---

## Configuração do banco de dados

Configure o arquivo `applcation.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario 
spring.datasource.password=sua_senha
 
spring.jpa.hibernate.ddl-auto=update
```

---

## Testes

Para rodar os testes:

```cmd
./mvnw test
```

---

## Licença

Esse projeto é apenas para fins de estudo.